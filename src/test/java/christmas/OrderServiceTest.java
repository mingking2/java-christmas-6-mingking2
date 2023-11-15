package christmas;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import christmas.dto.DateDTO;
import christmas.dto.OrderMenuRequest;
import christmas.model.Date;
import christmas.model.Order;
import christmas.model.OrderMenu;
import christmas.repository.OrderRepository;
import christmas.service.OrderService;
import christmas.util.Format;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void createOrder_shouldCreateOrderAndSaveToRepository() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("3");
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        when(orderRepository.save(any())).thenReturn(new Order(new Date(dateDTO), new OrderMenu(orderMenuRequest)));

        // Act
        Order result = orderService.createOrder(dateDTO, orderMenuRequest);

        // Assert
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dateDTO.getDate(), result.getDate().getDate()),
                () -> verify(orderRepository, times(1)).save(any())
        );
    }

    @Test
    void createDateOfVisit_shouldCreateDate() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("3");

        // Act
        Date result = orderService.createDateOfVisit(dateDTO);

        // Assert
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dateDTO.getDate(), result.getDate())
        );
    }

    @Test
    void createOrderMenu_shouldCreateOrderMenu() {
        // Arrange
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");

        // Act
        OrderMenu result = orderService.createOrderMenu(orderMenuRequest);

        // Assert
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(orderMenuRequest.getMenus(), result.getMenus())
        );
    }
}
