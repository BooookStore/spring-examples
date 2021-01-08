package com.example.spring.security.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationLogger {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationLogger.class.getName());

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        logger.info("login {}", event.getAuthentication().getName());
    }

}
