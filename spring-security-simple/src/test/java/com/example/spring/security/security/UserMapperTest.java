package com.example.spring.security.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

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
        jdbcTemplate.update("INSERT INTO user VALUES (null, 'john', '12345')");
    }

    @Test
    public void loadUser() {
        Optional<UserEntity> john = userMapper.findUserByUsername("john");
        assertThat(john.get().getUsername()).isEqualTo("john");
    }

}