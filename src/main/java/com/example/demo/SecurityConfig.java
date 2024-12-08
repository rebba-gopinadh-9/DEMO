package com.example.demo;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    // Configure CORS settings
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080") // Adjust this to your frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true);  // Allow sending cookies
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        JwtFilter jwtFilter = new JwtFilter();

        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtFilter);
        registrationBean.setUrlPatterns(Arrays.asList("/products", "/home","/products-page","/videos/sample","/video"));  // Protected endpoints
        return registrationBean;
    }
}
