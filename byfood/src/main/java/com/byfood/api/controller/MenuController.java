package com.byfood.api.controller;

import com.byfood.api.dto.MenuItemResponse;
import com.byfood.api.service.MenuItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class MenuController {

    private final MenuItemService service;

    public MenuController(MenuItemService service) {
        this.service = service;
    }

    @GetMapping("/menu")
    public ResponseEntity<Page<MenuItemResponse>> getMenu(Pageable pageable) {
        return ResponseEntity.ok(service.getAvailableMenu(pageable));
    }
}