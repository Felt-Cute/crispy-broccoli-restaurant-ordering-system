package com.dcat23.cb.restaurantordering.order.service;

import com.dcat23.cb.restaurantordering.menu.model.Menu;
import com.dcat23.cb.restaurantordering.menu.model.MenuItem;
import com.dcat23.cb.restaurantordering.menu.repository.MenuItemRepository;
import com.dcat23.cb.restaurantordering.order.dto.OrderCreationDto;
import com.dcat23.cb.restaurantordering.order.model.Order;
import com.dcat23.cb.restaurantordering.order.repository.OrderRepository;
import com.dcat23.cb.restaurantordering.user.model.User.User;
import com.dcat23.cb.restaurantordering.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {
    
    @Mock    
    private OrderRepository orderRepository;
    
    @Mock        
    private MenuItemRepository menuItemRepository;
    
    @Mock        
    private UserRepository userRepository;
    
    @InjectMocks
    private OrderServiceImpl orderService;

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
    @DisplayName("Create order")
    void givenOrderCreationDto_whenCreate_thenReturnOrderObject() {
        /* Given - order creation dto */
        OrderCreationDto creationDto = mock(OrderCreationDto.class);

        /* When - create */
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(mock(User.class)));
        when(menuItemRepository.findById(anyLong()))
                .thenReturn(Optional.of(mock(MenuItem.class)));
        when(orderRepository.save(any(Order.class)))
                .thenReturn(mock(Order.class));

        Order order = orderService.createOrder(creationDto);

        /* Then - return order object */
        Assertions.assertThat(order).isNotNull();
    }


}