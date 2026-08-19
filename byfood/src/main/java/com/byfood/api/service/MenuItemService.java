package com.byfood.api.service;

import com.byfood.api.dto.MenuItemRequest;
import com.byfood.api.dto.MenuItemResponse;
import com.byfood.api.exception.NotFoundException;
import com.byfood.api.mapper.MenuItemMapper;
import com.byfood.api.model.MenuItem;
import com.byfood.api.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository repository){
        this.menuItemRepository = repository;
    }

    public List<MenuItemResponse> getAvailableMenu(){
        return menuItemRepository.findByAvailableTrueOrderByCategoryAscNameAsc().stream().map(MenuItemMapper::toResponse)
                .toList();
    }

    public List<MenuItemResponse> getAllMenu() {
        return menuItemRepository.findAll().stream()
                .sorted(Comparator.comparing(MenuItem::getCategory)
                        .thenComparing(MenuItem::getName))
                .map(MenuItemMapper::toResponse)
                .toList();
    }

    public MenuItemResponse getMenuItem(Long id) {
        return menuItemRepository.findById(id)
                .map(MenuItemMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Menu item not found"));
    }

    public MenuItemResponse createMenuItem(MenuItemRequest request) {
        MenuItem item = MenuItemMapper.toEntity(request);
        return MenuItemMapper.toResponse(menuItemRepository.save(item));
    }

    public MenuItemResponse updateMenuItem(Long id, MenuItemRequest request) {
        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Menu item not found"));
        MenuItemMapper.toEntity(request, existing);
        return MenuItemMapper.toResponse(menuItemRepository.save(existing));
    }

    public void deleteMenuItem(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new NotFoundException("Menu item not found");
        }
        menuItemRepository.deleteById(id);
    }
}
