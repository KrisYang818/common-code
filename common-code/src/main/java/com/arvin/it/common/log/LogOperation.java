package com.arvin.it.common.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogOperation {

    // 日志模板（使用SpEL）
    String value();

    // 操作人（支持SpEL）
    String operator() default "#{T(com.arvin.it.utils.SecurityUtils).currentUserName()}";

    // 日志类型
    LogTypeEnum type() default LogTypeEnum.SYSTEM;

}
