package com.example.spring.security.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(
                User.withUsername("alice")
                        .password("12345")
                        .roles("admin")
                        .build()
        );
        userDetailsManager.createUser(
                User.withUsername("bob")
                        .password("12345")
                        .roles("staff")
                        .build()
        );
        return userDetailsManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests()
                .mvcMatchers("/admin/**").hasRole("admin")
                .mvcMatchers("/users/**").hasAnyRole("admin", "staff")
                .anyRequest().authenticated();
    }

}
