package com.dcat23.cb.restaurantordering.order.model;

import com.dcat23.cb.restaurantordering.menu.model.MenuItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    private static final Integer MIN_QUANTITY = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

//    @Transient
//    private Double subTotal;

    public OrderItem() {
        quantity = MIN_QUANTITY;
    }

    public Double getSubTotal() {
        return quantity * menuItem.getPrice();
    }

    public void setQuantity(Integer quantity) {
        this.quantity = Math.max(quantity, MIN_QUANTITY);
    }

    @Override
    public String toString() {
        Long orderId = order == null ? null: order.getId();
        return "OrderItem{" +
                "orderId=" + orderId +
                ", name=" + menuItem.getName() +
                ", quantity=" + quantity +
                ", subTotal=" + getSubTotal() +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        Long orderId = order == null ? null: order.getId();
        return Objects.equals(id, orderItem.id)
                && Objects.equals(quantity, orderItem.quantity)
                && Objects.equals(menuItem.getId(), orderItem.menuItem.getId())
                && Objects.equals(orderId, orderItem.order.getId());
    }

    @Override
    public int hashCode() {
        Long orderId = order == null ? null : order.getId();
        return Objects.hash(id, quantity, menuItem.getId(), orderId);
    }
}
