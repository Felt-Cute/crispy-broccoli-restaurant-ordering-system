package com.dcat23.cb.restaurantordering.user.repository;

import com.dcat23.cb.restaurantordering.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}
