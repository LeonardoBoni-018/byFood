package com.byfood.api.repository;

import com.byfood.api.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findFirstByOrderByIdAsc();
}