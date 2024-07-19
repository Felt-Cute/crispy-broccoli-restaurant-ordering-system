package com.dcat23.cb.restaurantordering.order.model;

import com.dcat23.cb.restaurantordering.user.model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false, name = "total_amount")
    private Double totalAmount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<OrderItem> orderItems;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    public Order() {
        orderItems = new HashSet<>();
        status = OrderStatus.PENDING;
        totalAmount = 0.0;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        orderItems.forEach(item -> item.setOrder(this));
        this.orderItems = orderItems;
    }
}
