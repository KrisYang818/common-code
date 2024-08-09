package com.arvin.it.common;

import com.arvin.it.config.RedisCache;
import com.arvin.it.entity.LoginUser;
import com.arvin.it.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1、获取token
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            // 放行
            filterChain.doFilter(request, response);
            return;
        }
        // 2、解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception exception) {
            LOGGER.error("token parse error", exception);
            throw new RuntimeException("token is error！");
        }

        // 3、从redis中获取用户信息
        String redisKey = "login:" + userId;
        LoginUser user = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(user)) {
            throw new RuntimeException("login error!!");
        }

        // 4、存入SecurityContextHolder
        // todo 获取权限信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
