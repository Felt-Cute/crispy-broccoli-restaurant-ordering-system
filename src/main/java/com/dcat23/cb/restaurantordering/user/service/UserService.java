package com.dcat23.cb.restaurantordering.user.service;

import com.dcat23.cb.restaurantordering.user.dto.*;
import com.dcat23.cb.restaurantordering.user.model.User;

import java.util.List;

public interface UserService {
    User registerUser(UserRegistrationDto userRegistration);

    JwtToken login(UserLoginDto userLogin);

    User getUserByUsername(String authentication);

    User updateUser(String username, UserUpdateDto updateDto);

    User getUserById(Long id);

    List<User> getAllUsers();
}
