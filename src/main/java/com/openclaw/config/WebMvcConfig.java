package com.openclaw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 确保/api/** 不被当作静态资源处理
        registry.addResourceHandler("/api/**")
                .addResourceLocations("classpath:/static/api/")
                .setCachePeriod(0)
                .resourceChain(false);
    }
}
