package com.dcat23.cb.restaurantordering;

import com.dcat23.cb.restaurantordering.menu.dto.MenuItemDto;
import com.dcat23.cb.restaurantordering.menu.model.Menu;
import com.dcat23.cb.restaurantordering.menu.repository.MenuRepository;
import com.dcat23.cb.restaurantordering.user.model.Role;
import com.dcat23.cb.restaurantordering.user.model.User;
import com.dcat23.cb.restaurantordering.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class RestaurantOrderingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantOrderingApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(MenuRepository menuRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Menu initialMenu = initMenu();
            menuRepository.save(initialMenu);

            String pwd = passwordEncoder.encode("password");
            User admin = createAdmin(pwd);
            User staff = createStaff(pwd);
            User customer = createCustomer(pwd);
            userRepository.saveAll(List.of(admin, staff, customer));

        };
    }

    private User createCustomer(String password) {
        User customer = new User();
        customer.setUsername("customer");
        customer.setPassword(password);
        customer.setEmail("customer@myself.com");
        customer.addRoles(Role.CUSTOMER);
        return customer;
    }

    private User createStaff(String password) {
        User staff = new User();
        staff.setUsername("staff");
        staff.setPassword(password);
        staff.setEmail("staff@myself.com");
        staff.addRoles(Role.STAFF, Role.CUSTOMER);
        return staff;
    }

    private User createAdmin(String password) {
        User user = new User();
        user.setUsername("dcat");
        user.setPassword(password);
        user.setEmail("dcat@myself.com");
        user.addRoles(Role.ADMIN, Role.STAFF, Role.CUSTOMER);
        return user;
    }

    private Menu initMenu() {
        Menu menu = new Menu();
        menu.setName("Main Menu");
        menu.setDescription("Our daily menu");

        Set<MenuItemDto> items = new HashSet<>();
        items.add(new MenuItemDto("Vegetable Samosa", "Mildly spiced potatoes wrapped in a light pastry", 5.99, "Appetizers", ""));
        items.add(new MenuItemDto("Tomato Soup", "Fresh tomatoes crushed and tampered with fresh Indian spices", 5.99, "Soups", ""));
        items.add(new MenuItemDto("Rasmalai", "Soft cheese patties soaked in milk, cardamom, and rose water syrup", 4.99, "Deserts", ""));
        items.add(new MenuItemDto("Bottled water", "", 1.99, "Appetizers", ""));

        menu.addAllMenuItems(items);
        return menu;
    }

}
