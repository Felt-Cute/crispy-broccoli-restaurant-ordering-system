package com.dcat23.cb.restaurantordering.order.exception;

import com.dcat23.cb.restaurantordering.core.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
//@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(InvalidStatusTransitionException.class)
    public ResponseEntity<ErrorMessage> invalidStatusTransitionExceptionExceptionHandler(
            InvalidStatusTransitionException e,
            HttpServletRequest request
    ) {
        ErrorMessage errorMessage = new ErrorMessage(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
