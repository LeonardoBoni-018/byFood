package com.byfood.api.repository;

import com.byfood.api.model.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByAvailableTrue();

    Page<MenuItem> findByAvailableTrueOrderByCategoryAscNameAsc(Pageable pageable);

    Page<MenuItem> findAllByOrderByCategoryAscNameAsc(Pageable pageable);
}