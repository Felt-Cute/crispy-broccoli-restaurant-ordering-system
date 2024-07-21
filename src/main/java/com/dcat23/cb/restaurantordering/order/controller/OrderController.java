package com.dcat23.cb.restaurantordering.order.controller;

import com.dcat23.cb.restaurantordering.order.dto.OrderCreationDto;
import com.dcat23.cb.restaurantordering.order.dto.OrderStatusUpdateDto;
import com.dcat23.cb.restaurantordering.order.model.Order;
import com.dcat23.cb.restaurantordering.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Order> createOrder(@RequestBody OrderCreationDto orderDto) {
        Order order = orderService.createOrder(orderDto);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> order = orderService.getAllOrders();
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'STAFF', 'ADMIN')")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{user-id}")
    @PreAuthorize("hasRole('CUSTOMER') or #id == authentication.principal.id")
    public ResponseEntity<Set<Order>> getOrdersByUser(@PathVariable(name = "user-id") Long id) {
        Set<Order> orders = orderService.getOrdersByUser(id);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatusUpdateDto statusDto) {
        Order order = orderService.updateOrderStatus(id, statusDto.status());
        return ResponseEntity.ok(order);
    }

}
