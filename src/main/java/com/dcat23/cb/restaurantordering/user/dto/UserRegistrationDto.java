package com.dcat23.cb.restaurantordering.user.dto;

public record UserRegistrationDto(
        String username,
        String email,
        String password
) {
}