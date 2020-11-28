package com.example.spring.security.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyBatisUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    public MyBatisUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userMapper.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles("ADMIN")
                .build();
    }

}
