package com.dcat23.cb.restaurantordering.user.service;

import com.dcat23.cb.restaurantordering.user.dto.UserLoginDto;
import com.dcat23.cb.restaurantordering.user.dto.UserRegistrationDto;
import com.dcat23.cb.restaurantordering.user.dto.UserUpdateDto;
import com.dcat23.cb.restaurantordering.user.model.User;

import java.util.List;

public interface UserService {
    User registerUser(UserRegistrationDto userRegistration);

    User login(UserLoginDto userLogin);

    User getUserByUsername(String authentication);

    User updateUser(String username, UserUpdateDto updateDto);

    User getUserById(Long id);

    List<User> getAllUsers();
}
