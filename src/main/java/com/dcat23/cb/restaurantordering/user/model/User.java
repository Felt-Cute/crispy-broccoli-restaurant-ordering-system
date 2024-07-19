package com.dcat23.cb.restaurantordering.user.model;

import com.dcat23.cb.restaurantordering.order.model.Order;
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

    @Column(nullable = false, name = "password_hash")
    private String passwordHash;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY
    )
    private Set<Order> orders = new HashSet<>();

    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }
}
