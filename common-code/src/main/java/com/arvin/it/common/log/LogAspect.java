package com.arvin.it.common.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    /**
     * SpEL解析器
     */
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();

    @Around("@annotation(logOperation)")
    public Object logAround(ProceedingJoinPoint joinPoint, LogOperation logOperation) throws Throwable {
        // 前置处理(解析参数，构建EL上下文对象)
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Object[] args = joinPoint.getArgs();
        EvaluationContext context = createEvaluationContext(method, args);

        // 解析操作人（支持SpEL）
        String operator = parseEL(context, logOperation.operator());

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 后置处理
        String value = parseEL(context, logOperation.value(), result);
        // 记录日志（异步保存道数据库）
        saveLog(logOperation.type(), operator, value);
        return result;
    }

    private EvaluationContext createEvaluationContext(Method method, Object[] args) {
        EvaluationContext context = new StandardEvaluationContext();
        String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(method);
        for (int i = 0; i < args.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        return context;
    }

    private String parseEL(EvaluationContext context, String expression) {
        return PARSER.parseExpression(expression, new TemplateParserContext()).getValue(context, String.class);
    }

    private String parseEL(EvaluationContext context, String expression, Object result) {
        if (expression.contains("#result")) {
            context.setVariable("result", result);
        }
        return PARSER.parseExpression(expression, new TemplateParserContext()).getValue(context, String.class);
    }

    /**
     * 异步保存日志
     * @param type 日志类型
     * @param operator 操作人
     * @param value 操作日志
     */
    @Async
    public void saveLog(LogTypeEnum type, String operator, String value) {
        LogEntity entity = new LogEntity();
        entity.setType(type.name());
        entity.setOperator(operator);
        entity.setContent(value);
        LOGGER.info("operaLog: {}", entity);
    }
}
