package com.dcat23.cb.restaurantordering.menu.model;

import com.dcat23.cb.restaurantordering.menu.dto.MenuItemDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false, name = "image_url")
    private String imageUrl;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public static MenuItem from(MenuItemDto menuItemDto) {
        Objects.requireNonNull(menuItemDto, "menuItemDto must not be null");
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDto.name());
        menuItem.setDescription(menuItemDto.description());
        menuItem.setPrice(menuItemDto.price());
        menuItem.setCategory(menuItemDto.category());
        menuItem.setImageUrl(menuItemDto.imageUrl());
        return menuItem;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(name, menuItem.name)
                && Objects.equals(description, menuItem.description)
                && Objects.equals(category, menuItem.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, category);
    }

    public void setPrice(Double price) {
        this.price = Math.max(price, 0.0);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", menuId=" + menu.getId() +
                '}';
    }
}
