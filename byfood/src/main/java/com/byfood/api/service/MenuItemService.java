package com.byfood.api.service;

import com.byfood.api.dto.MenuItemRequest;
import com.byfood.api.dto.MenuItemResponse;
import com.byfood.api.exception.NotFoundException;
import com.byfood.api.mapper.MenuItemMapper;
import com.byfood.api.model.MenuItem;
import com.byfood.api.repository.MenuItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository repository) {
        this.menuItemRepository = repository;
    }

    public Page<MenuItemResponse> getAvailableMenu(Pageable pageable) {
        return menuItemRepository.findByAvailableTrueOrderByCategoryAscNameAsc(pageable)
                .map(MenuItemMapper::toResponse);
    }

    public Page<MenuItemResponse> getAllMenu(Pageable pageable) {
        return menuItemRepository.findAllByOrderByCategoryAscNameAsc(pageable)
                .map(MenuItemMapper::toResponse);
    }

    public MenuItemResponse getMenuItem(Long id) {
        return menuItemRepository.findById(id)
                .map(MenuItemMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Item do cardápio não encontrado"));
    }

    public MenuItemResponse createMenuItem(MenuItemRequest request) {
        MenuItem item = MenuItemMapper.toEntity(request);
        return MenuItemMapper.toResponse(menuItemRepository.save(item));
    }

    public MenuItemResponse updateMenuItem(Long id, MenuItemRequest request) {
        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item do cardápio não encontrado"));
        MenuItemMapper.toEntity(request, existing);
        return MenuItemMapper.toResponse(menuItemRepository.save(existing));
    }

    public void deleteMenuItem(Long id) {
        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item do cardápio não encontrado"));
        existing.setAvailable(false);
        menuItemRepository.save(existing);
    }
}