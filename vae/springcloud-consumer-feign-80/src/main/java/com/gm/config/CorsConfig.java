package com.gm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//推荐写这种
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//允许的路径
                .allowedOrigins("*")//允许的源
                .maxAge(1800)
                .allowedMethods("POST","PUT","GET","DELETE","OPTIONS")
                .allowedHeaders("*")//允许的处理器对象
                .allowCredentials(true);// 允许发送Cookie
    }
}
