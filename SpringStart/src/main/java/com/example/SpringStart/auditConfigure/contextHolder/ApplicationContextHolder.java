package com.example.SpringStart.auditConfigure.contextHolder;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext staticApplicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        staticApplicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> aClass) {
        return Optional.ofNullable(aClass)
                .map(staticApplicationContext::getBean)
                .orElse(null);
    }
}