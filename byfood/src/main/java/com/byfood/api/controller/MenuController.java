package com.byfood.api.controller;

import com.byfood.api.dto.MenuItemResponse;
import com.byfood.api.service.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class MenuController {

    private final MenuItemService service;

    public MenuController(MenuItemService service) {
        this.service = service;
    }

    @GetMapping("/menu")
    public ResponseEntity<List<MenuItemResponse>> getMenu() {
        return ResponseEntity.ok(service.getAvailableMenu());
    }
}
