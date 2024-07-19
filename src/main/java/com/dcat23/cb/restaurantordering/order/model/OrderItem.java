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

    public OrderItem() {
        quantity = MIN_QUANTITY;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = Math.max(quantity, MIN_QUANTITY);
    }

    public String getName() {
        return menuItem.getName();
    }

    public Long getOrderId() {
        return order == null ? null : order.getId();
    }

    public Double getSubTotal() {
        return quantity * menuItem.getPrice();
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId=" + getOrderId() +
                ", name=" + getName() +
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
        return Objects.equals(id, orderItem.id)
                && Objects.equals(quantity, orderItem.quantity)
                && Objects.equals(menuItem.getId(), orderItem.menuItem.getId())
                && Objects.equals(getOrderId(), orderItem.order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, menuItem.getId(), getOrderId());
    }
}
