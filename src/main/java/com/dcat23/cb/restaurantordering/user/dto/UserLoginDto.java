package com.dcat23.cb.restaurantordering.user.dto;

import jakarta.validation.constraints.Size;

public record UserLoginDto(
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        String username,

        @Size(min = 8, max = 100,message = "Password must have at least 8 characters")
        String password
) {
}
