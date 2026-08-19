package com.byfood.api.controller;

import com.byfood.api.TestcontainersConfiguration;
import com.byfood.api.config.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
class AdminOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    @Test
    void shouldRejectWithoutToken() throws Exception {
        mockMvc.perform(get("/admin/orders"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldAllowWithToken() throws Exception {
        String token = jwtService.generateToken("admin");

        mockMvc.perform(get("/admin/orders")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundForUnknownOrderStatusUpdate() throws Exception {
        String token = jwtService.generateToken("admin");

        mockMvc.perform(put("/admin/orders/9999/status")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"status":"PREPARING"}
                                """))
                .andExpect(status().isNotFound());
    }
}