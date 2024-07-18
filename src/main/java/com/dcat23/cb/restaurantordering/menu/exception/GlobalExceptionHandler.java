package com.dcat23.cb.restaurantordering.menu.exception;

import com.dcat23.cb.restaurantordering.core.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> genericExceptionHandler(
            Exception e,
            HttpServletRequest request
    ) {
        String message = String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage());
        log.error(message);
        ErrorMessage errorMessage = new ErrorMessage(
                request.getRequestURI(),
                message,
                HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {
        String validationErrors = e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(","));

        String message = "Validation errors: " + validationErrors;

        log.error(message);

        ErrorMessage errorMessage = new ErrorMessage(
                request.getRequestURI(),
                message,
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
