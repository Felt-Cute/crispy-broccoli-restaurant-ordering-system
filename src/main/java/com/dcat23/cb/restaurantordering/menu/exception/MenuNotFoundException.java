package com.dcat23.cb.restaurantordering.menu.exception;

public class MenuNotFoundException extends RuntimeException {
    public MenuNotFoundException(Long id) {
        super(String.format("Menu not found with id: %s", id));
    }
}
