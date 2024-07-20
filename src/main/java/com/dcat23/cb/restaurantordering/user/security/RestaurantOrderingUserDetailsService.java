package com.dcat23.cb.restaurantordering.user.security;

import com.dcat23.cb.restaurantordering.user.model.Role;
import com.dcat23.cb.restaurantordering.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantOrderingUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public RestaurantOrderingUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param username the username identifying the user whose data is required
     * @return UserDetails
     * @throws UsernameNotFoundException if username is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.dcat23.cb.restaurantordering.user.model.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(Role::authority)
                .toList();

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
