package com.moulh.pg.core.config;

import com.moulh.pg.core.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName : AuthConfig
 * @Author : moulh
 * @Date : 2021-07-11 11:57
 * @Version : V1.0
 * @Description :
 */
@Configuration
public class AuthConfig implements WebMvcConfigurer {
    @Bean
    public AuthInterceptor initAuthInterceptor(){
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(initAuthInterceptor()).addPathPatterns("/**").excludePathPatterns("/login/**","/open/**","/doc.html/**","/swagger-resources","/swagger-resources/**","/webjars/**","/v2/**");
    }
}
