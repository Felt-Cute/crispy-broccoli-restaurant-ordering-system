package com.dcat23.cb.restaurantordering.menu.service;

import com.dcat23.cb.restaurantordering.menu.dto.MenuCreationDto;
import com.dcat23.cb.restaurantordering.menu.dto.MenuUpdateDto;
import com.dcat23.cb.restaurantordering.menu.model.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    /**
     * @param menuUpdateDto
     * @return
     */
    @Override
    public Menu updateMenu(MenuUpdateDto menuUpdateDto) {
        return null;
    }

    /**
     * @param id Menu ID
     * @return
     */
    @Override
    public Menu deleteMenu(String id) {
        return null;
    }

    /**
     * @param menuCreationDto
     * @return
     */
    @Override
    public Menu createMenu(MenuCreationDto menuCreationDto) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Menu> getMenus() {
        return List.of();
    }

    /**
     * @param id Menu ID
     * @return
     */
    @Override
    public Menu getMenuById(String id) {
        return null;
    }
}
