package com.example.spring.security.security;

import com.example.spring.security.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DocumentPermissionEvaluator implements PermissionEvaluator {

    private final Logger logger = LoggerFactory.getLogger(DocumentPermissionEvaluator.class.getName());

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        System.out.println(Thread.currentThread().getId());
        logger.info("evaluate permission based on {} with permission {}", targetDomainObject, permission);

        // permission と同じ authority を保持しているユーザーを認可する
        if (authentication.getAuthorities().stream().anyMatch(auth -> auth.equals(new SimpleGrantedAuthority(permission.toString())))) {
            return true;
        }

        var document = (Document) targetDomainObject;
        var username = authentication.getName();

        if (document.getOwnerUsername().equals(username)) {
            return true;
        } else if (document.getReviewerUsername().equals(username)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

}
