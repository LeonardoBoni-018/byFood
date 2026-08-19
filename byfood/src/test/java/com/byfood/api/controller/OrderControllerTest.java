package com.byfood.api.controller;

import com.byfood.api.TestcontainersConfiguration;
import com.byfood.api.dto.MenuItemRequest;
import com.byfood.api.service.MenuItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MenuItemService menuItemService;

    @Test
    void shouldCreateOrder() throws Exception {
        Long itemId = menuItemService.createMenuItem(new MenuItemRequest(
                "Burger", "desc", new BigDecimal("25.00"), "Main", null, true)).id();

        mockMvc.perform(post("/public/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "customerName": "John Doe",
                                  "customerPhone": "+5511999998888",
                                  "customerAddress": "Rua A, 10",
                                  "items": [{"menuItemId": %d, "quantity": 2}]
                                }
                                """.formatted(itemId)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("RECEIVED"))
                .andExpect(jsonPath("$.total").value(50.00))
                .andExpect(jsonPath("$.whatsappLink").value(org.hamcrest.Matchers.startsWith("https://wa.me/5511988888888?text=")));
    }

    @Test
    void shouldReturnNotFoundForUnknownOrder() throws Exception {
        mockMvc.perform(get("/public/orders/9999"))
                .andExpect(status().isNotFound());
    }
}