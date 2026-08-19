package com.byfood.api.controller;

import com.byfood.api.dto.OrderRequest;
import com.byfood.api.dto.OrderResponse;
import com.byfood.api.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/public/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest request) {
        OrderResponse created = service.createOrder(request);
        return ResponseEntity.created(URI.create("/public/orders/" + created.id())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrder(id));
    }
}