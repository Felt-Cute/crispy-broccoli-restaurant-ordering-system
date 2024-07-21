package com.dcat23.cb.restaurantordering.core.exception;

import com.dcat23.cb.restaurantordering.menu.exception.MenuItemNotFoundException;
import com.dcat23.cb.restaurantordering.menu.exception.MenuNotFoundException;
import com.dcat23.cb.restaurantordering.order.exception.InvalidStatusTransitionException;
import com.dcat23.cb.restaurantordering.user.exception.UserAlreadyExistsException;
import com.dcat23.cb.restaurantordering.user.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {
        String validationErrors = e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        String message = "Validation errors: " + validationErrors;

        log.error(message);

        ErrorMessage errorMessage = new ErrorMessage(
                request.getRequestURI(),
                validationErrors,
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> dataIntegrityViolationExceptionExceptionHandler(
            DataIntegrityViolationException e,
            HttpServletRequest request
    ) {

        String message = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();

        log.error(message);

        ErrorMessage errorMessage = new ErrorMessage(
                request.getRequestURI(),
                message,
                HttpStatus.CONFLICT,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    /*
    *
    *
    * MENU EXCEPTIONS
    *
    * */

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

    @ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<ErrorMessage> menuItemNotFoundExceptionHandler(
            MenuItemNotFoundException e,
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

    /*
    *
    * ORDER EXCEPTIONS
    *
    * */

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

        /*
        *
        * USER EXCEPTIONS
        *
        * */


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> userAlreadyExistsExceptionHandler(
            UserAlreadyExistsException e,
            HttpServletRequest request
    ) {
        ErrorMessage errorMessage = new ErrorMessage(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.CONFLICT,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorMessage> authorizationDeniedExceptionExceptionHandler(
            AuthorizationDeniedException e,
            HttpServletRequest request
    ) {
        ErrorMessage errorMessage = new ErrorMessage(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.FORBIDDEN,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFoundExceptionHandler(
            UserNotFoundException e,
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
