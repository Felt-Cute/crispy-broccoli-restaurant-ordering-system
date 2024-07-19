package com.dcat23.cb.restaurantordering.order.repository;

import com.dcat23.cb.restaurantordering.order.model.Order;
import com.dcat23.cb.restaurantordering.user.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
