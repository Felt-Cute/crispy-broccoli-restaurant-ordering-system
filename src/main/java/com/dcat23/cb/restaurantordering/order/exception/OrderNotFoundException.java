package com.dcat23.cb.restaurantordering.order.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super(String.format("Order id %s not found", id));
    }
}
