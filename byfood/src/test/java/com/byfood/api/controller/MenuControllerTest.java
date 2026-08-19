package com.byfood.api.controller;

import com.byfood.api.TestcontainersConfiguration;
import com.byfood.api.config.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    @Test
    void shouldAllowPublicMenuWithoutToken() throws Exception {
        mockMvc.perform(get("/public/menu"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldRejectAdminMenuWithoutToken() throws Exception {
        mockMvc.perform(get("/admin/menu"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldAllowAdminMenuWithToken() throws Exception {
        String token = jwtService.generateToken("admin");

        mockMvc.perform(get("/admin/menu")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}