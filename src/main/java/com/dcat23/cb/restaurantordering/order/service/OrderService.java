package com.dcat23.cb.restaurantordering.order.service;

import com.dcat23.cb.restaurantordering.order.dto.OrderCreationDto;
import com.dcat23.cb.restaurantordering.order.dto.OrderStatusUpdateDto;
import com.dcat23.cb.restaurantordering.order.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderCreationDto orderDto);

    Order getOrderById(Long id);

    List<Order> getOrdersByUser(Long id);

    Order updateOrderStatus(Long id, OrderStatusUpdateDto statusUpdate);

    List<Order> getAllOrders();
}
