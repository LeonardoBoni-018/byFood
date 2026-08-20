package com.byfood.api.controller;

import com.byfood.api.dto.OrderResponse;
import com.byfood.api.dto.OrderStatusUpdateRequest;
import com.byfood.api.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderService service;

    public AdminOrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAllOrders(pageable));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateStatus(@PathVariable Long id,
                                                      @Valid @RequestBody OrderStatusUpdateRequest request) {
        return ResponseEntity.ok(service.updateOrderStatus(id, request.status()));
    }
}