package com.dcat23.cb.restaurantordering.order.service;

import com.dcat23.cb.restaurantordering.order.dto.OrderCreationDto;
import com.dcat23.cb.restaurantordering.order.dto.OrderStatusUpdateDto;
import com.dcat23.cb.restaurantordering.order.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    /**
     * @param orderDto
     * @return
     */
    @Override
    public Order createOrder(OrderCreationDto orderDto) {

        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<Order> getOrdersByUser(Long id) {
        return List.of();
    }

    /**
     * @param id
     * @param statusUpdate
     * @return
     */
    @Override
    public Order updateOrderStatus(Long id, OrderStatusUpdateDto statusUpdate) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return List.of();
    }
}
