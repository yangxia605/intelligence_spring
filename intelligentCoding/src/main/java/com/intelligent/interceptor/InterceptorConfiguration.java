package com.intelligent.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
/*        registry.addInterceptor(new LoginInterceptor())
                // 拦截除了登录页面和js, css库之外的所有URL
                .addPathPatterns("/**")
                .excludePathPatterns("/register", "/login");*/
    }
}