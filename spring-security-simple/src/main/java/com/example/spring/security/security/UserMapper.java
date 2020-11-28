package com.example.spring.security.security;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Mapper
public interface UserMapper {

    Optional<UserEntity> findUserByUsername(String username);

    List<String> findRolesByUserId(int userId);

}
