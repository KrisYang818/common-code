package com.arvin.it;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.web.DefaultSecurityFilterChain;

@SpringBootApplication
@MapperScan(basePackages = "com.arvin.it.mapper")
public class Main {
    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
        Class<DefaultSecurityFilterChain> defaultSecurityFilterChainClass = DefaultSecurityFilterChain.class;
        DefaultSecurityFilterChain bean = run.getBean(defaultSecurityFilterChainClass);
        System.out.println(bean);
        System.out.println("Hello world!");
    }
}