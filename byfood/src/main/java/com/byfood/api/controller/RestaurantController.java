package com.byfood.api.controller;

import com.byfood.api.dto.RestaurantResponse;
import com.byfood.api.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/restaurant")
    public ResponseEntity<RestaurantResponse> getRestaurant() {
        return ResponseEntity.ok(service.getRestaurant());
    }
}