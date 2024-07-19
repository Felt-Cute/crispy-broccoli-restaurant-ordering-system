package com.dcat23.cb.restaurantordering.menu.service;

import com.dcat23.cb.restaurantordering.menu.dto.MenuCreationDto;
import com.dcat23.cb.restaurantordering.menu.dto.MenuUpdateDto;
import com.dcat23.cb.restaurantordering.menu.model.Menu;
import com.dcat23.cb.restaurantordering.menu.repository.MenuRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
        MenuCreationDto creationDto = mock(MenuCreationDto.class);

        /* When - create */
        when(menuRepository.save(any(Menu.class)))
                .thenReturn(mock(Menu.class));
        Menu menu = menuService.createMenu(creationDto);

        /* Then - menu created */
        assertThat(menu).isNotNull();
    }

    @Test
    @DisplayName("Update menu")
    void givenMenuUpdateDto_whenUpdate_thenReturnMenu() {
        /* Given - menu update dto */
        MenuUpdateDto updateDto = mock(MenuUpdateDto.class);

        /* When - update */
        when(menuRepository.findById(anyLong()))
                .thenReturn(Optional.of(mock(Menu.class)));
        when(menuRepository.save(any(Menu.class)))
                .thenReturn(mock(Menu.class));
        Menu menu = menuService.updateMenu(anyLong(), updateDto);

        /* Then - return menu */
        assertThat(menu).isNotNull();
    }

}