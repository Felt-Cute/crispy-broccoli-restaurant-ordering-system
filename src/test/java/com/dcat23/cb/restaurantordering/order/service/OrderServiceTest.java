package com.dcat23.cb.restaurantordering.order.service;

import com.dcat23.cb.restaurantordering.menu.model.MenuItem;
import com.dcat23.cb.restaurantordering.menu.service.MenuService;
import com.dcat23.cb.restaurantordering.order.dto.OrderCreationDto;
import com.dcat23.cb.restaurantordering.order.model.Order;
import com.dcat23.cb.restaurantordering.order.model.OrderStatus;
import com.dcat23.cb.restaurantordering.order.repository.OrderRepository;
import com.dcat23.cb.restaurantordering.user.model.User;
import com.dcat23.cb.restaurantordering.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {
    
    @Mock    
    private OrderRepository orderRepository;
    
    @Mock        
    private MenuService menuService;
    
    @Mock        
    private UserService userService;
    
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
        when(userService.getUserById(anyLong()))
                .thenReturn(mock(User.class));
        when(menuService.getMenuItemById(anyLong()))
                .thenReturn(mock(MenuItem.class));
        when(orderRepository.save(any(Order.class)))
                .thenReturn(mock(Order.class));

        Order order = orderService.createOrder(creationDto);

        /* Then - return order object */
        assertThat(order).isNotNull();
    }


    @Test
    @DisplayName("Update order status")
    void givenIdAndStatus_whenUpdate_thenSuccess() {
        /* Given - id and status */
        OrderStatus updateToStatus = OrderStatus.PREPARING;
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);

        /* When - update */
        when(orderRepository.findById(anyLong()))
            .thenReturn(Optional.of(order));

        when(orderRepository.save(any(Order.class)))
                .thenReturn(mock(Order.class));

        Order actual = orderService.updateOrderStatus(orderId, updateToStatus);

        /* Then - success */
        assertThat(actual).isNotNull();
        assertThat(actual.getStatus()).isEqualTo(updateToStatus);
    }


}