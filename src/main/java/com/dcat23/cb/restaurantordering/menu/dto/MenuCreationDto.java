package com.dcat23.cb.restaurantordering.menu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record MenuCreationDto(

        @NotBlank(message = "Name must not be empty")
        String name,

        String description,

        @Size(min = 1, message = "Menu must have at least 1 item")
        Set<MenuItemDto> menuItems
) {
}
