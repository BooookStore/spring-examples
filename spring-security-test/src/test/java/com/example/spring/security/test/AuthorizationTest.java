package com.example.spring.security.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void accessWithNoAuthentication() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    public void accessWithUserHasNotRole() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "admin")
    public void accessWithUserHasAdminRole() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "staff")
    public void accessWithUserHasStaffRole() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("bob")
    public void accessWithBobToNoPermitPath() throws Exception {
       mockMvc.perform(get("/admin"))
               .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("alice")
    public void accessWithBobToPermitPath() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }

}
