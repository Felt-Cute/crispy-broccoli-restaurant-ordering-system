package com.dcat23.cb.restaurantordering.order.service;

import com.dcat23.cb.restaurantordering.order.dto.OrderCreationDto;
import com.dcat23.cb.restaurantordering.order.model.Order;
import com.dcat23.cb.restaurantordering.order.model.OrderStatus;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderCreationDto orderDto);

    Order getOrderById(Long id);

    List<Order> getOrdersByUser(Long userId);

    Order updateOrderStatus(Long id, OrderStatus statusUpdate);

    List<Order> getAllOrders();
}
