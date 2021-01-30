package com.example.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @SuppressWarnings("SpellCheckingInspection")
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(User.builder()
                .username("Sato")
                .password("Sato")
                .roles("normal")
                .build()
        );
        userDetailsManager.createUser(User.builder()
                .username("Tanaka")
                .password("Tanaka")
                .roles("normal")
                .build()
        );
        userDetailsManager.createUser(User.builder()
                .username("Kurihasi")
                .password("Kurihasi")
                .roles("normal")
                .build()
        );
        userDetailsManager.createUser(User.builder()
                .username("Suzuki")
                .password("Suzuki")
                .roles("normal")
                .build()
        );
        userDetailsManager.createUser(User.builder()
                .username("Maede")
                .password("Maede")
                .roles("normal")
                .build()
        );
        userDetailsManager.createUser(User.builder()
                .username("Takahasi")
                .password("Takahasi")
                .roles("admin")
                .build()
        );

        return userDetailsManager;
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().mvcMatchers("document/**").authenticated();
    }

}
