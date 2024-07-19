package com.dcat23.cb.restaurantordering.menu.service;

import com.dcat23.cb.restaurantordering.menu.dto.MenuCreationDto;
import com.dcat23.cb.restaurantordering.menu.dto.MenuUpdateDto;
import com.dcat23.cb.restaurantordering.menu.exception.MenuNotFoundException;
import com.dcat23.cb.restaurantordering.menu.model.Menu;
import com.dcat23.cb.restaurantordering.menu.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /**
     * @param id Menu id
     * @param menuUpdateDto DTO for Menu fields
     * @return updated Menu object
     */
    @Override
    public Menu updateMenu(Long id, MenuUpdateDto menuUpdateDto) {
        Menu menu = getMenuById(id);

        if (menuUpdateDto.name() != null) {
            menu.setName(menuUpdateDto.name());
        }

        if (menuUpdateDto.description() != null) {
            menu.setDescription(menuUpdateDto.description());
        }

        if (menuUpdateDto.menuItems() != null)
        {
            menu.setMenuItems(menuUpdateDto.menuItems());
        }

        return menuRepository.save(menu);
    }

    /**
     * @param id Menu id
     * @return the removed Menu object
     */
    @Override
    public Menu deleteMenu(Long id) {
        Menu menu = getMenuById(id);
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
