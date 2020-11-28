package com.example.spring.security.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@SpringBootTest
@Transactional
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void beforeEach() {
        // insert user
        var userId = jdbcTemplate.queryForObject("SELECT nextval('users_id')", Integer.class);
        jdbcTemplate.update("INSERT INTO users (id, username, password) VALUES (?, ?, ?)", userId, "john", "12345");

        // insert role
        jdbcTemplate.update("INSERT INTO roles (id, name) VALUES (?, ?)", userId, "ADMIN");
    }

    @Test
    public void loadUser() {
        Optional<UserEntity> john = userMapper.findUserByUsername("john");
        assertThat(john.get().getUsername()).isEqualTo("john");

        List<String> roles = userMapper.findRolesByUserId(john.get().getId());
        assertThat(roles).hasSize(1).contains("ADMIN");
    }

}