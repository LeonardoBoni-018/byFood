package com.byfood.api.service;

import com.byfood.api.TestcontainersConfiguration;
import com.byfood.api.dto.MenuItemRequest;
import com.byfood.api.dto.MenuItemResponse;
import com.byfood.api.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class MenuItemServiceTest {

    @Autowired
    private MenuItemService service;

    private MenuItemRequest sampleRequest() {
        return new MenuItemRequest(
                "Pizza Margherita",
                "Tomato, mozzarella and basil",
                new BigDecimal("39.90"),
                "Pizza",
                null,
                true);
    }

    @Test
    void shouldCreateAndReadMenuItem() {
        MenuItemResponse created = service.createMenuItem(sampleRequest());

        assertThat(created.id()).isNotNull();
        assertThat(created.name()).isEqualTo("Pizza Margherita");

        MenuItemResponse found = service.getMenuItem(created.id());
        assertThat(found.price()).isEqualByComparingTo("39.90");
    }

    @Test
    void shouldUpdateMenuItem() {
        MenuItemResponse created = service.createMenuItem(sampleRequest());

        MenuItemRequest update = new MenuItemRequest(
                "Pizza Margherita XL",
                "Tomato, mozzarella and basil",
                new BigDecimal("49.90"),
                "Pizza",
                null,
                false);

        MenuItemResponse updated = service.updateMenuItem(created.id(), update);

        assertThat(updated.name()).isEqualTo("Pizza Margherita XL");
        assertThat(updated.price()).isEqualByComparingTo("49.90");
        assertThat(updated.available()).isFalse();
    }

    @Test
    void shouldDeleteMenuItem() {
        MenuItemResponse created = service.createMenuItem(sampleRequest());

        service.deleteMenuItem(created.id());

        assertThatThrownBy(() -> service.getMenuItem(created.id()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void shouldThrowNotFoundForUnknownId() {
        assertThatThrownBy(() -> service.getMenuItem(9999L))
                .isInstanceOf(NotFoundException.class);
    }
}