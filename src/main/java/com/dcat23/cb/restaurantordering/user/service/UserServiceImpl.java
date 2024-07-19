package com.dcat23.cb.restaurantordering.user.service;

import com.dcat23.cb.restaurantordering.core.utils.Sanitize;
import com.dcat23.cb.restaurantordering.user.dto.UserLoginDto;
import com.dcat23.cb.restaurantordering.user.dto.UserRegistrationDto;
import com.dcat23.cb.restaurantordering.user.dto.UserUpdateDto;
import com.dcat23.cb.restaurantordering.user.exception.UserAlreadyExistsException;
import com.dcat23.cb.restaurantordering.user.exception.UserNotFoundException;
import com.dcat23.cb.restaurantordering.user.model.User;
import com.dcat23.cb.restaurantordering.user.model.UserRole;
import com.dcat23.cb.restaurantordering.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param registrationDTO object with User data
     * @return the created User entity
     */
    @Override
    @Transactional
    public User registerUser(UserRegistrationDto registrationDTO) {
        String lowerCasedName = Sanitize.lower(registrationDTO.username());
        if (userRepository.existsByUsername(lowerCasedName)) {
            throw new UserAlreadyExistsException(lowerCasedName);
        }

        User user = new User();
        user.setUsername(registrationDTO.username());
        user.setEmail(registrationDTO.email());
        user.setPassword(passwordEncoder.encode(registrationDTO.password()));
        user.setRole(UserRole.CUSTOMER);

        return userRepository.save(user);

    }

    /**
     * @param userLogin login details
     * @return the authorized User
     */
    @Override
    public User login(UserLoginDto userLogin) {
        return null;
    }

    /**
     * @param username to check repository for
     * @throws UserNotFoundException if not found
     * @return User entity from repository
     */
    @Override
    public User getUserByUsername(String username) {
        String lowerCasedName = Sanitize.lower(username);
        return userRepository.findByUsername(lowerCasedName)
                .orElseThrow(() -> new UserNotFoundException(lowerCasedName));
    }

    /**
     * @param username of User to update
     * @param updateDto fields to change
     * @return the updated User object
     */
    @Override
    public User updateUser(String username, UserUpdateDto updateDto) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User getUserById(Long id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
