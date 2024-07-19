package com.dcat23.cb.restaurantordering.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(String.format("User %d not found", id));
    }
}
