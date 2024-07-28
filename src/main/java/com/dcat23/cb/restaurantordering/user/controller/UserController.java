package com.dcat23.cb.restaurantordering.user.controller;

import com.dcat23.cb.restaurantordering.user.dto.JwtToken;
import com.dcat23.cb.restaurantordering.user.dto.UserLoginDto;
import com.dcat23.cb.restaurantordering.user.dto.UserRegistrationDto;
import com.dcat23.cb.restaurantordering.user.dto.UserUpdateDto;
import com.dcat23.cb.restaurantordering.user.model.User;
import com.dcat23.cb.restaurantordering.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dcat23.cb.restaurantordering.user.security.SecurityConstants.JWT_HEADER;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserRegistrationDto userRegistration) {
        User user = userService.registerUser(userRegistration);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@Valid @RequestBody UserLoginDto userLogin) {
        JwtToken jwt = userService.login(userLogin);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(JWT_HEADER, jwt.accessToken())
                .body(jwt);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateUserProfile(Authentication authentication, @RequestBody UserUpdateDto updateDto) {
        String username = authentication.getName();
        User updatedUser = userService.updateUser(username, updateDto);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
