package com.dcat23.cb.restaurantordering.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserRegistrationDto(
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        String username,

        @Email
        String email,

        @Size(min = 8, max = 100, message = "Password must have at least 8 characters")
        String password
) {
}
