package com.dcat23.cb.restaurantordering.user.model;

import com.dcat23.cb.restaurantordering.core.utils.Sanitize;
import com.dcat23.cb.restaurantordering.order.model.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false, name = "pwd_hash")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY
    )
    private Set<Order> orders = new HashSet<>();

    public User() {
        role = UserRole.CUSTOMER;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    public void setUsername(String username) {
        this.username = Sanitize.lower(username);
    }

    public void setEmail(String email) {
        this.email = Sanitize.lower(email);
    }
}
