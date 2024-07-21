package com.dcat23.cb.restaurantordering.order.service;

import com.dcat23.cb.restaurantordering.menu.model.MenuItem;
import com.dcat23.cb.restaurantordering.menu.service.MenuService;
import com.dcat23.cb.restaurantordering.order.dto.OrderCreationDto;
import com.dcat23.cb.restaurantordering.order.dto.OrderItemDto;
import com.dcat23.cb.restaurantordering.order.exception.InvalidStatusTransitionException;
import com.dcat23.cb.restaurantordering.order.exception.OrderNotFoundException;
import com.dcat23.cb.restaurantordering.order.model.Order;
import com.dcat23.cb.restaurantordering.order.model.OrderItem;
import com.dcat23.cb.restaurantordering.order.model.OrderStatus;
import com.dcat23.cb.restaurantordering.order.repository.OrderRepository;
import com.dcat23.cb.restaurantordering.user.model.User;
import com.dcat23.cb.restaurantordering.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MenuService menuService;
    private final UserService userService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, MenuService menuService, UserService userService) {
        this.orderRepository = orderRepository;
        this.menuService = menuService;
        this.userService = userService;
    }

    /**
     * @param orderDto initial order data DTO
     * @return newly created Order
     */
    @Override
    @Transactional
    public Order createOrder(OrderCreationDto orderDto) {
        Order order = new Order();
        orderDto.items().stream()
                .map(this::createOrderItem)
                .forEach(order::addItem);

        User user = userService.getUserById(orderDto.userId());
        user.addOrder(order);

        return orderRepository.save(order);
    }

    private OrderItem createOrderItem(OrderItemDto orderItemDto) {
        MenuItem menuItem = menuService.getMenuItemById(orderItemDto.menuItemId());

        OrderItem orderItem = new OrderItem();
        orderItem.setMenuItem(menuItem);
        orderItem.setQuantity(orderItemDto.quantity());

        return orderItem;
    }

    /**
     * @param id the Order id
     * @throws OrderNotFoundException if order with id is not found in repository
     * @return the Order object if it exists in the repository
     */
    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    /**
     * @param userId the id of the User object
     * @return orders by the user
     */
    @Override
    public Set<Order> getOrdersByUser(Long userId) {
        User user = userService.getUserById(userId);
        return user.getOrders();
    }

    /**
     * @param id the Order id
     * @param statusUpdate DTO object with OrderStatus enum
     * @return the Order with updated status
     */
    @Override
    @Transactional
    public Order updateOrderStatus(Long id, OrderStatus statusUpdate) {
        Order order = getOrderById(id);

        if (order.getStatus().equals(statusUpdate)) return order;

        if (!isValidStatusTransition(order.getStatus(), statusUpdate)) {
            throw new InvalidStatusTransitionException(order.getStatus(), statusUpdate );
        }

        order.setStatus(statusUpdate);
        orderRepository.save(order);
        return order;
    }

    private boolean isValidStatusTransition(OrderStatus current, OrderStatus updateTo) {
        return switch (current) {
            case PENDING -> updateTo == OrderStatus.PREPARING || updateTo == OrderStatus.CANCELLED;
            case PREPARING -> updateTo == OrderStatus.READY || updateTo == OrderStatus.CANCELLED;
            case READY -> updateTo == OrderStatus.DELIVERED;
            default -> false;
        };
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
