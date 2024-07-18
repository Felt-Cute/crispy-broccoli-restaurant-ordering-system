package com.dcat23.cb.restaurantordering.menu.service;

import com.dcat23.cb.restaurantordering.menu.dto.MenuCreationDto;
import com.dcat23.cb.restaurantordering.menu.dto.MenuItemDto;
import com.dcat23.cb.restaurantordering.menu.model.Menu;
import com.dcat23.cb.restaurantordering.menu.repository.MenuRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuServiceImpl menuService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }


    @Test
    @DisplayName("Create menu")
    void givenMenuCreationDto_whenCreate_thenMenuCreated() {
        /* Given - menu creation dto */
        MenuCreationDto creationDto = getCreationDto();

        /* When - create */
        when(menuRepository.save(any(Menu.class)))
                .thenReturn(mock(Menu.class));
        Menu menu = menuService.createMenu(creationDto);

        /* Then - menu created */
        assertThat(menu).isNotNull();
    }

    private static MenuCreationDto getCreationDto() {
        Set<MenuItemDto> menuItems = new HashSet<>();
        MenuItemDto philly = new MenuItemDto(
                "Philly Cheese Steak",
                "Classic philly cheese steak",
                11.99,
                "Entrees",
                ""
        );

        MenuItemDto pizza = new MenuItemDto(
                "Peperoni pizza",
                "Large pizza",
                12.99,
                "Entrees",
                ""
        );

        MenuItemDto tea = new MenuItemDto(
                "Sweet tea",
                "sweet tea",
                1.99,
                "Beverages",
                ""
        );

        menuItems.add(philly);
        menuItems.add(pizza);
        menuItems.add(tea);

        return new MenuCreationDto(
                "Test Menu",
                "Super delicious menu",
                menuItems
        );
    }


}