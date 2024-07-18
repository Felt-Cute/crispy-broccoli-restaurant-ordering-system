package com.dcat23.cb.restaurantordering.menu.service;

import com.dcat23.cb.restaurantordering.menu.dto.MenuCreationDto;
import com.dcat23.cb.restaurantordering.menu.dto.MenuUpdateDto;
import com.dcat23.cb.restaurantordering.menu.model.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenus();

    Menu getMenuById(String id);

    Menu createMenu(MenuCreationDto menuCreationDto);

    Menu updateMenu(MenuUpdateDto menuUpdateDto);

    Menu deleteMenu(String id);
}
