package com.dcat23.cb.restaurantordering.menu.model;


import com.dcat23.cb.restaurantordering.menu.dto.MenuItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "menu",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<MenuItem> menuItems;

    public void addItem(MenuItemDto menuItemDto) {
        if (menuItems == null) {
            menuItems = new HashSet<>();
        }

        MenuItem menuItem = MenuItem.of(menuItemDto);
        menuItem.setMenu(this);
        menuItems.add(menuItem);
    }
}
