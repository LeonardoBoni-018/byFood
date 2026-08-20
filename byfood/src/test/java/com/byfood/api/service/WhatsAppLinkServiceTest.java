package com.byfood.api.service;

import com.byfood.api.model.Order;
import com.byfood.api.model.OrderItem;
import com.byfood.api.model.OrderStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class WhatsAppLinkServiceTest {

    private final WhatsAppLinkService service = new WhatsAppLinkService();

    @Test
    void shouldBuildLinkWithItemsTotalsAndBrFormatting() {
        Order order = Order.builder()
                .customerName("João")
                .customerPhone("+55 11 99999-8888")
                .customerAddress("Rua A, 10")
                .status(OrderStatus.RECEIVED)
                .total(new BigDecimal("56.00"))
                .build();
        order.addItem(OrderItem.builder()
                .itemName("Burger")
                .unitPrice(new BigDecimal("25.00"))
                .quantity(2)
                .build());
        order.addItem(OrderItem.builder()
                .itemName("Soda")
                .unitPrice(new BigDecimal("6.00"))
                .quantity(1)
                .build());

        String link = service.buildOrderLink(order, "Restaurante Teste", "+55 11 98888-8888");

        assertThat(link).startsWith("https://wa.me/5511988888888?text=");

        String message = URLDecoder.decode(link.substring(link.indexOf("text=") + 5), StandardCharsets.UTF_8)
                .replace('\u00A0', ' ');

        assertThat(message).contains("Olá! Restaurante Teste");
        assertThat(message).contains("2x Burger - R$ 25,00 = R$ 50,00");
        assertThat(message).contains("1x Soda - R$ 6,00 = R$ 6,00");
        assertThat(message).contains("Total: R$ 56,00");
        assertThat(message).contains("Nome: João");
        assertThat(message).contains("Endereço: Rua A, 10");
    }
}