package com.example.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // API 경로 설정
                        .allowedOrigins("http://localhost:8080") // Vue 애플리케이션 주소
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 메소드
                        .allowedHeaders("*") // 허용할 헤더
                        .allowCredentials(true); // 쿠키를 사용할 경우 설정
            }
        };
    }
}