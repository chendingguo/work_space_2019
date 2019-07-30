package com.oristartech.cmc.demo.config;

import com.oristartech.cmc.demo.interceptor.OpLogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.oristartech.cmc.demo.interceptor.AuthorizationInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor();
    }

    /*
     * 操作日志拦截器
     */
    @Bean
    public OpLogInterceptor opLogInterceptor() {
        return new OpLogInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(opLogInterceptor()).addPathPatterns("/**");
    }
}
