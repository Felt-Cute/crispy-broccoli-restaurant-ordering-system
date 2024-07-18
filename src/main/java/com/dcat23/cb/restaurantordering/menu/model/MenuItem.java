package com.dcat23.cb.restaurantordering.menu.model;

import com.dcat23.cb.restaurantordering.menu.dto.MenuItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false, name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public static MenuItem of(MenuItemDto menuItemDto) {
        return builder()
                .name(menuItemDto.name())
                .description(menuItemDto.description())
                .price(menuItemDto.price())
                .category(menuItemDto.category())
                .imageUrl(menuItemDto.imageUrl())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(name, menuItem.name)
                && Objects.equals(description, menuItem.description)
                && Objects.equals(price, menuItem.price)
                && Objects.equals(category, menuItem.category)
                && Objects.equals(imageUrl, menuItem.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, category, imageUrl);
    }
}
