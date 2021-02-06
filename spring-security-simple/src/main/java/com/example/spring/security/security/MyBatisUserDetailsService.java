package com.example.spring.security.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyBatisUserDetailsService implements UserDetailsService {

    private final SecurityUserMapper securityUserMapper;

    public MyBatisUserDetailsService(SecurityUserMapper securityUserMapper) {
        this.securityUserMapper = securityUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = securityUserMapper.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));

        List<String> roles = securityUserMapper.findRolesByUserId(userEntity.getId());

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(roles.toArray(String[]::new))
                .build();
    }

}
