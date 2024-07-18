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
     * @param id
     * @param menuUpdateDto
     * @return
     */
    @Override
    public Menu updateMenu(Long id, MenuUpdateDto menuUpdateDto) {
        Menu menuById = getMenuById(id);
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
     * Creates a new menu based on the provided menu creation details.
     *
     * @param menuCreationDto the data transfer object containing the details
     *                        for creating a new menu, including the name,
     *                        description, and items of the menu
     * @return the created Menu object that has been saved to the repository
     */
    @Override
    public Menu createMenu(MenuCreationDto menuCreationDto) {
        Menu menu = new Menu();
        menu.setName(menuCreationDto.name());
        menu.setDescription(menuCreationDto.description());
        menu.addAllMenuItems(menuCreationDto.menuItems());
        return menuRepository.save(menu);
    }

    /**
     * @return list of Menu objects that have been saved to the repository
     */
    @Override
    public List<Menu> getMenus() {
        return menuRepository.findAll();
    }

    /**
     * @throws MenuNotFoundException if id is not found in repository
     * @param id Menu id
     * @return Menu object
     */
    @Override
    public Menu getMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new MenuNotFoundException(id));
    }
}
