package com.dcat23.cb.restaurantordering.order.service;

import com.dcat23.cb.restaurantordering.order.dto.OrderCreationDto;
import com.dcat23.cb.restaurantordering.order.dto.OrderStatusUpdateDto;
import com.dcat23.cb.restaurantordering.order.model.Order;
import com.dcat23.cb.restaurantordering.order.repository.OrderRepository;
import com.dcat23.cb.restaurantordering.user.exception.UserNotFoundException;
import com.dcat23.cb.restaurantordering.user.model.User.User;
import com.dcat23.cb.restaurantordering.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
//        order.setUser(user);

        return orderRepository.save(order);
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
        return List.of();
    }
}
