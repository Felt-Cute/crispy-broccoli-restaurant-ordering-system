package com.dcat23.cb.restaurantordering.menu.dto;

import jakarta.validation.constraints.NotBlank;

public record MenuCreationDto(
        @NotBlank String name,
        @NotBlank String description
) {
}
