package com.ohgiraffers.mergyping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 정적 리소스 경로 설정
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:C:/lecture/MERGEPING/02-namhojung/uploads/");
    }
}
