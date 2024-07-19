package com.dcat23.cb.restaurantordering.menu.model;


import com.dcat23.cb.restaurantordering.menu.dto.MenuItemDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Data
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
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<MenuItem> menuItems;


    public Menu() {
        menuItems = new HashSet<>();
    }

    public void addMenuItem(MenuItemDto menuItemDto) {
        if (menuItems == null) {
            menuItems = new HashSet<>();
        }
        MenuItem menuItem = MenuItem.of(menuItemDto);
        menuItem.setMenu(this);
        menuItems.remove(menuItem);
        menuItems.add(menuItem);
    }

    public void addAllMenuItems(Set<MenuItemDto> menuItemDtos) {
        menuItemDtos.forEach(this::addMenuItem);
    }

    public void setMenuItems(Set<MenuItemDto> menuItemDtos) {
        menuItems.clear();
        addAllMenuItems(menuItemDtos);
    }
}
