package com.dcat23.cb.restaurantordering.order.dto;

import java.util.List;

public record OrderCreationDto(
        Long userId,
        List<OrderItemDto> items
) {
}
