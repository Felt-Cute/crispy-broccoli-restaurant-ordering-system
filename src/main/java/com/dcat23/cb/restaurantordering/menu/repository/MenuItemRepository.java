package com.dcat23.cb.restaurantordering.menu.repository;

import com.dcat23.cb.restaurantordering.menu.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
