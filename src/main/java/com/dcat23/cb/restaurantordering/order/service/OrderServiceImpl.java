package com.dcat23.cb.restaurantordering.order.service;

import com.dcat23.cb.restaurantordering.menu.exception.MenuItemNotFoundException;
import com.dcat23.cb.restaurantordering.menu.model.MenuItem;
import com.dcat23.cb.restaurantordering.menu.repository.MenuItemRepository;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, MenuItemRepository menuItemRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
        this.userRepository = userRepository;
    }

    /**
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public Order createOrder(OrderCreationDto orderDto) {
        User user = getUserByUserId(orderDto.userId());
        Order order = new Order();
        orderDto.items().stream()
                .map(this::createOrderItem)
                .forEach(order::addItem);
        order.setUser(user);

        return orderRepository.save(order);
    }

    private OrderItem createOrderItem(OrderItemDto orderItemDto) {
        MenuItem menuItem = menuItemRepository.findById(orderItemDto.menuItemId())
                .orElseThrow(() -> new MenuItemNotFoundException(orderItemDto.menuItemId()));

        OrderItem orderItem = new OrderItem();
        orderItem.setMenuItem(menuItem);
        orderItem.setQuantity(orderItemDto.quantity());

        return orderItem;
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
        User user = getUserByUserId(userId);

        return List.of();
    }

    private User getUserByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
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
        return orderRepository.findAll();
    }
}
