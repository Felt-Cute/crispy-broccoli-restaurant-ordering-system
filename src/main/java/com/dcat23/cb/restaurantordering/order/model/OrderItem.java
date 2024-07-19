package com.dcat23.cb.restaurantordering.order.model;

import com.dcat23.cb.restaurantordering.menu.model.MenuItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuItem menuItem;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem() {
        quantity = 1;
    }

    @Transient
    public Double getSubTotal() {
        return quantity * menuItem.getPrice();
    }

    public void setQuantity(Integer quantity) {
        this.quantity = Math.max(quantity, 1);
    }
}
