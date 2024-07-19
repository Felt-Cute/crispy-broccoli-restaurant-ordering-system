package com.dcat23.cb.restaurantordering.menu.exception;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException(Long id) {
        super(String.format("MenuItem not found with id: %s", id));
    }
}
