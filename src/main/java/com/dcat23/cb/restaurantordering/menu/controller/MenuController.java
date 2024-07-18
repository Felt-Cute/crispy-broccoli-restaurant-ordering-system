package com.dcat23.cb.restaurantordering.menu.controller;

import com.dcat23.cb.restaurantordering.menu.dto.MenuCreationDto;
import com.dcat23.cb.restaurantordering.menu.dto.MenuUpdateDto;
import com.dcat23.cb.restaurantordering.menu.model.Menu;
import com.dcat23.cb.restaurantordering.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getMenus() {
        List<Menu> menus = menuService.getMenus();
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long id) {
        Menu menu = menuService.getMenuById(id);
        return ResponseEntity.ok(menu);
    }

    @PostMapping
    public ResponseEntity<Menu> createMenu(@RequestBody MenuCreationDto menuCreationDto) {
        Menu menu = menuService.createMenu(menuCreationDto);
        return ResponseEntity.ok(menu);
    }

    @PutMapping
    public ResponseEntity<Menu> updateMenu(@RequestBody MenuUpdateDto menuUpdateDto) {
        Menu menu = menuService.updateMenu(menuUpdateDto);
        return ResponseEntity.ok(menu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Menu> deleteMenu(@PathVariable Long id) {
        Menu menu = menuService.deleteMenu(id);
        return ResponseEntity.ok(menu);
    }

}
