package com.dcat23.cb.restaurantordering.menu.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record MenuItemDto(
        @NotBlank(message = "Name must not be empty")
        String name,

        @NotBlank(message = "Description must not be empty")
        String description,

        @Min(value = 0, message = "Price must be greater than 0")
        @NotBlank(message = "Price must not be empty")
        Double price,

        @NotBlank(message = "Category must not be empty")
        String category,

        String imageUrl
) {
}
