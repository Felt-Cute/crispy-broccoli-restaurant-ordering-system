package com.dcat23.cb.restaurantordering.order.repository;

import com.dcat23.cb.restaurantordering.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
