package com.example.spring.security.repository;

import com.example.spring.security.model.User;
import com.example.spring.security.repository.mapper.RoleMapper;
import com.example.spring.security.repository.mapper.RoleTable;
import com.example.spring.security.repository.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private final UserMapper userMapper;

    private final RoleMapper roleMapper;

    public UserRepository(UserMapper userMapper, RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    public List<User> findAll() {
        return userMapper.findAll().stream()
                .map(ut -> {
                    var roles = roleMapper.findById(ut.getId()).stream().map(RoleTable::getName).collect(Collectors.toList());
                    return new User(ut.getId(), ut.getUsername(), ut.getEmailAddress(), roles);
                })
                .collect(Collectors.toList());
    }

}
