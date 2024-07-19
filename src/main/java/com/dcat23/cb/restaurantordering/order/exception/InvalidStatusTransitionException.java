package com.dcat23.cb.restaurantordering.order.exception;

import com.dcat23.cb.restaurantordering.order.model.OrderStatus;

public class InvalidStatusTransitionException extends RuntimeException {
    public InvalidStatusTransitionException(OrderStatus current, OrderStatus desired) {
        super(String.format("Cannot transition from %s to %s", current, desired));
    }
}
