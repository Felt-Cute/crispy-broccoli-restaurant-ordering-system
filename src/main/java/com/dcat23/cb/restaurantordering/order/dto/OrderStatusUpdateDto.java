package com.dcat23.cb.restaurantordering.order.dto;

import com.dcat23.cb.restaurantordering.order.model.OrderStatus;

public record OrderStatusUpdateDto(
        OrderStatus status
) {
}
