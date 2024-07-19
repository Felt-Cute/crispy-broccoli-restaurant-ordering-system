package com.dcat23.cb.restaurantordering.menu.dto;

import java.util.Set;

public record MenuUpdateDto(

        String name,

        String description,

        Set<MenuItemDto> menuItems
) {
}
