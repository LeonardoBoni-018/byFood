package com.byfood.api.service;

import com.byfood.api.TestcontainersConfiguration;
import com.byfood.api.dto.MenuItemRequest;
import com.byfood.api.dto.OrderItemRequest;
import com.byfood.api.dto.OrderRequest;
import com.byfood.api.dto.OrderResponse;
import com.byfood.api.exception.NotFoundException;
import com.byfood.api.model.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MenuItemService menuItemService;

    private Long createMenuItem(String name, BigDecimal price) {
        return menuItemService.createMenuItem(new MenuItemRequest(
                name, "desc", price, "Main", null, true)).id();
    }

    @Test
    void shouldCreateOrderWithComputedTotal() {
        Long itemId = createMenuItem("Burger", new BigDecimal("25.00"));

        OrderRequest request = new OrderRequest(
                "John Doe", "+5511999998888", "Rua A, 10",
                List.of(new OrderItemRequest(itemId, 2)));

        OrderResponse created = orderService.createOrder(request);

        assertThat(created.id()).isNotNull();
        assertThat(created.status()).isEqualTo(OrderStatus.RECEIVED);
        assertThat(created.total()).isEqualByComparingTo("50.00");
        assertThat(created.items()).hasSize(1);
        assertThat(created.items().get(0).itemName()).isEqualTo("Burger");
    }

    @Test
    void shouldReadCreatedOrder() {
        Long itemId = createMenuItem("Pizza", new BigDecimal("39.90"));

        OrderResponse created = orderService.createOrder(new OrderRequest(
                "Jane Doe", "+5511999997777", "Rua B, 20",
                List.of(new OrderItemRequest(itemId, 1))));

        OrderResponse found = orderService.getOrder(created.id());

        assertThat(found.customerName()).isEqualTo("Jane Doe");
        assertThat(found.total()).isEqualByComparingTo("39.90");
    }

    @Test
    void shouldThrowNotFoundForUnknownOrder() {
        assertThatThrownBy(() -> orderService.getOrder(9999L))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void shouldThrowNotFoundForUnknownMenuItem() {
        OrderRequest request = new OrderRequest(
                "John Doe", "+5511999998888", "Rua A, 10",
                List.of(new OrderItemRequest(9999L, 1)));

        assertThatThrownBy(() -> orderService.createOrder(request))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void shouldUpdateOrderStatus() {
        Long itemId = createMenuItem("Soda", new BigDecimal("6.00"));

        OrderResponse created = orderService.createOrder(new OrderRequest(
                "John Doe", "+5511999998888", "Rua A, 10",
                List.of(new OrderItemRequest(itemId, 1))));

        OrderResponse updated = orderService.updateOrderStatus(created.id(), OrderStatus.PREPARING);

        assertThat(updated.status()).isEqualTo(OrderStatus.PREPARING);
    }
}