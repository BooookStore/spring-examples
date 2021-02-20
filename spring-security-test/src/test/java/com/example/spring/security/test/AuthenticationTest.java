package com.example.spring.security.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void formLoginWithInvalidCredential() throws Exception {
        mockMvc.perform(formLogin().user("bob").password("11111"))
                .andExpect(unauthenticated());
    }

    @Test
    public void formLoginWithValidCredential() throws Exception {
        mockMvc.perform(formLogin().user("bob").password("12345"))
                .andExpect(authenticated().withRoles("staff"));
    }

}
