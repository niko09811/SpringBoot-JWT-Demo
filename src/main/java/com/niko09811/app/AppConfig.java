package com.niko09811.app;

import com.niko09811.app.utils.JwtUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${app.jwt.secret}")
    private String yourPropertyValue;

    @PostConstruct
    public void init() {
        JwtUtil.setSecret(yourPropertyValue);
    }
}