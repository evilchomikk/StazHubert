package com.example.SpringStart.auditConfigure.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrentUserProviderConfig {

    @Bean
    CurrentUserProvider getUserProvider() {
        return new CurrentUserProvider();
    }

}
