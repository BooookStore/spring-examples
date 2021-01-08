package com.example.spring.security.security;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Mapper
public interface UserMapper {

    Optional<UserEntity> findUserByUsername(String username);

    List<String> findRolesByUserId(int userId);

    void updateUser(UserEntity userEntity);

    void insertUser(UserEntity newUserEntity);

    void insertRole(@Param("id") int id, @Param("role") String role);

    int generateNextIdentity();

}
