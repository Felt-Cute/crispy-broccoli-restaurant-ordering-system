package com.dcat23.cb.restaurantordering.menu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record MenuUpdateDto(

        String name,

        String description,

        Set<MenuItemDto> menuItems
) {
}
