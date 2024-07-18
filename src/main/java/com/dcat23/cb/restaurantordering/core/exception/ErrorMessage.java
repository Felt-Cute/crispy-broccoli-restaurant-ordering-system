package com.dcat23.cb.restaurantordering.core.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorMessage(
        String path,
        String message,
        HttpStatus statusCode,
        LocalDateTime timestamp
) {
}
