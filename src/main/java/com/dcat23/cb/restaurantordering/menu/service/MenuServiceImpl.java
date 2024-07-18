package com.dcat23.cb.restaurantordering.menu.service;

import com.dcat23.cb.restaurantordering.menu.dto.MenuCreationDto;
import com.dcat23.cb.restaurantordering.menu.dto.MenuUpdateDto;
import com.dcat23.cb.restaurantordering.menu.exception.MenuNotFoundException;
import com.dcat23.cb.restaurantordering.menu.model.Menu;
import com.dcat23.cb.restaurantordering.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

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
    public Menu deleteMenu(Long id) {
        Menu menu = this.getMenuById(id);
        menuRepository.delete(menu);
        return menu;
    }

    /**
     * @param menuCreationDto
     * @return
     */
    @Override
    public Menu createMenu(MenuCreationDto menuCreationDto) {
        Menu menu = Menu.builder()
                .name(menuCreationDto.name())
                .description(menuCreationDto.description())
                .build();
//        menuCreationDto.menuItems().forEach(menu::addItem);
        return menuRepository.save(menu);
    }

    /**
     * @return
     */
    @Override
    public List<Menu> getMenus() {
        return menuRepository.findAll();
    }

    /**
     * @param id Menu ID
     * @return
     */
    @Override
    public Menu getMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new MenuNotFoundException(id));
    }
}
