package com.byfood.api.repository;

import com.byfood.api.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByAvailableTrue();

    List<MenuItem> findByAvailableTrueOrderByCategoryAscNameAsc();
}