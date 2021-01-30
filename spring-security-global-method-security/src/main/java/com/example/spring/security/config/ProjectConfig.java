package com.example.spring.security.config;

import com.example.spring.security.security.DocumentPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ProjectConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private DocumentPermissionEvaluator documentPermissionEvaluator;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        var expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(documentPermissionEvaluator);
        return expressionHandler;
    }

}
