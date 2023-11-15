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
import christmas.dto.OrderResponse;
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
        orderRepository = new OrderRepository();
        orderService = new OrderService(orderRepository);
        orderRepository.clear();
    }

    @Test
    void createOrder_shouldCreateOrderAndSaveToRepository() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("3");
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order order = new Order(new Date(dateDTO), new OrderMenu(orderMenuRequest));
        orderRepository.save(order);

        // Act
        OrderResponse result = orderService.createOrder(dateDTO, orderMenuRequest);
        Order bringOrder = orderRepository.findById(result.getId());

        // Assert
        assertNotNull(result);
        assertEquals(dateDTO.getDate(), bringOrder.getDate().getDate());
        assertEquals(orderMenuRequest.getMenus(), bringOrder.getOrderMenu().getMenus());
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
