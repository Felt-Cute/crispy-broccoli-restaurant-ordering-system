package com.dcat23.cb.restaurantordering.order.service;

import com.dcat23.cb.restaurantordering.order.dto.OrderCreationDto;
import com.dcat23.cb.restaurantordering.order.dto.OrderItemDto;
import com.dcat23.cb.restaurantordering.order.dto.OrderStatusUpdateDto;
import com.dcat23.cb.restaurantordering.order.exception.OrderNotFoundException;
import com.dcat23.cb.restaurantordering.order.model.Order;
import com.dcat23.cb.restaurantordering.order.model.OrderItem;
import com.dcat23.cb.restaurantordering.order.repository.OrderRepository;
import com.dcat23.cb.restaurantordering.user.exception.UserNotFoundException;
import com.dcat23.cb.restaurantordering.user.model.User.User;
import com.dcat23.cb.restaurantordering.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
//    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public Order createOrder(OrderCreationDto orderDto) {
//        User user = getUserByUserId(orderDto.userId());
        Order order = new Order();
        Set<OrderItem> orderItems = orderDto.items().stream()
                .map(this::createOrderItem)
                .collect(Collectors.toSet());
//        order.setUser(user);
        order.setOrderItems(orderItems);

        return orderRepository.save(order);
    }

    private OrderItem createOrderItem(OrderItemDto orderItemDto) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<Order> getOrdersByUser(Long userId) {
//        User user = getUserByUserId(userId);

        return List.of();
    }

//    private User getUserByUserId(Long userId) {
//        return userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException(userId));
//    }

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
        return orderRepository.findAll();
    }
}
