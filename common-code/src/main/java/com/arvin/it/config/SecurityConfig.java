package com.arvin.it.config;

import com.arvin.it.common.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 该加密工具特点：原文一样，但是每次加密的结果不一样。
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * CSRF 是跨站请求伪造（CSRF）是web常见的攻击之一。
     * SpringSecurity去防止CSRF攻击的方式就是通过csrf_token。后端生成一个csrf_token。前端发起请求的时候需要携带这个csrf_token，后端会有过滤器进行校验，如果没有携带或者是伪造的就不允许访问。
     * 我们可以发现CSRF攻击依靠的是cookie中所携带的认证信息，但是在前端后端分离的项目中我们的认证信息其实是token，而token并不是存储在cookie中，并且需要前端代码去把token设置到请求头中才可以，
     * 所以CSRF攻击就不用担心了。
     * CSRF：本质上 攻击者不会获取到用户凭证信息，而是冒充受害者去操作、发请求。
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/user/login").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(withDefaults());
        return http.build();
    }
}
