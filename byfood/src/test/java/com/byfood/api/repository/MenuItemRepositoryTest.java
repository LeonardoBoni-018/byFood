package com.byfood.api.repository;

import com.byfood.api.TestcontainersConfiguration;
import com.byfood.api.model.MenuItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class MenuItemRepositoryTest {

    @Autowired
    private MenuItemRepository repository;

    @Test
    void shouldSaveAndReadMenuItem() {
        MenuItem item = MenuItem.builder()
                .name("Burger")
                .description("Beef burger")
                .price(new BigDecimal("19.90"))
                .category("Main")
                .available(true)
                .build();

        MenuItem saved = repository.save(item);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCreatedAt()).isNotNull();
        assertThat(repository.findById(saved.getId()))
                .get()
                .extracting(MenuItem::getName)
                .isEqualTo("Burger");
        assertThat(repository.findByAvailableTrue())
                .extracting(MenuItem::getName)
                .contains("Burger");
    }
}
