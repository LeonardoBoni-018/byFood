package com.byfood.api.service;

import com.byfood.api.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class RestaurantServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    void shouldReturnSeededRestaurant() {
        var response = service.getRestaurant();
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("My Restaurant");
    }
}
