package com.dcat23.cb.restaurantordering.menu.exception;

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
public class MenuExceptionHandler {

    @ExceptionHandler(MenuNotFoundException.class)
    public ResponseEntity<ErrorMessage> menuNotFoundExceptionHandler(
            MenuNotFoundException e,
            HttpServletRequest request
    ) {
        ErrorMessage errorMessage = new ErrorMessage(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }


}
