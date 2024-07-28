package com.dcat23.cb.restaurantordering.user.service;

import com.dcat23.cb.restaurantordering.core.utils.Sanitize;
import com.dcat23.cb.restaurantordering.user.dto.*;
import com.dcat23.cb.restaurantordering.user.exception.UserAlreadyExistsException;
import com.dcat23.cb.restaurantordering.user.exception.UserNotFoundException;
import com.dcat23.cb.restaurantordering.user.model.Role;
import com.dcat23.cb.restaurantordering.user.model.User;
import com.dcat23.cb.restaurantordering.user.repository.UserRepository;
import com.dcat23.cb.restaurantordering.user.security.JwtTokenGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

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
        user.addRoles(Role.CUSTOMER);

        return userRepository.save(user);

    }

    /**
     * @param userLogin login details
     * @return the authorized User
     */
    @Override
    public JwtToken login(UserLoginDto userLogin) {
        Authentication auth = authenticateLogin(userLogin);
        if (auth == null || !auth.isAuthenticated()) {
            throw new BadCredentialsException("Username or password is incorrect");
        }
        return JwtTokenGenerator.generateToken(auth, jwtSecret);
    }

    private Authentication authenticateLogin(UserLoginDto userLoginDTO) {
        return authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        userLoginDTO.username(),
                        userLoginDTO.password()
                )
        );
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
     * @param id the user's id
     * @return the User Object
     */
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * @return List of Users
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
