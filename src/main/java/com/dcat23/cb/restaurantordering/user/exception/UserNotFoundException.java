package com.dcat23.cb.restaurantordering.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(String.format("User id '%d' not found", id));
    }

    public UserNotFoundException(String username) {
        super(String.format("User '%s' not found", username));
    }
}
