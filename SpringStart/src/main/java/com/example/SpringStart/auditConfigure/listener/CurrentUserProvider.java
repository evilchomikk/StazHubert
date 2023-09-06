package com.example.SpringStart.auditConfigure.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

public class CurrentUserProvider {

    @Bean
    User getCurrentUser() {
        return (User) getContext().getAuthentication().getPrincipal();
    }
}
