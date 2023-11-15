package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import christmas.constant.DiscountConstant;
import christmas.dto.DateDTO;
import christmas.dto.OrderMenuRequest;
import christmas.model.Date;
import christmas.model.Order;
import christmas.model.OrderMenu;
import christmas.model.discount.ChristmasDiscount;
import christmas.model.discount.Discount;
import christmas.model.discount.GiftPriceDiscount;
import christmas.model.discount.SpecialDiscount;
import christmas.model.discount.WeekdayDiscount;
import christmas.model.discount.WeekendDiscount;
import christmas.repository.OrderRepository;
import christmas.service.DiscountService;
import christmas.util.Format;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiscountServiceTest {

    private OrderRepository orderRepository;
    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        discountService = new DiscountService(orderRepository);
    }

    @Test
    void applyAllDiscounts_shouldApplyAllDiscounts() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("3");
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order order = new Order(new Date(dateDTO), new OrderMenu(orderMenuRequest));
        when(orderRepository.findById(1L)).thenReturn(order);

        // Act
        discountService.applyAllDiscounts();

        // Assert
        assertEquals(4, discountService.getDiscounts().size());
    }

    @Test
    void createChristmasDiscount_shouldCreateChristmasDiscount() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("25");
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order order = new Order(new Date(dateDTO), new OrderMenu(orderMenuRequest));
        when(orderRepository.findById(1L)).thenReturn(order);

        // Act
        discountService.applyAllDiscounts();
        Discount christmasDiscount = discountService.createChristmasDiscount();

        // Assert
        assertNotNull(christmasDiscount);
        assertEquals(ChristmasDiscount.class, christmasDiscount.getClass());
    }

    @Test
    void createWeekdayDiscount_shouldCreateWeekdayDiscount() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("7");
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order order = new Order(new Date(dateDTO), new OrderMenu(orderMenuRequest));
        when(orderRepository.findById(1L)).thenReturn(order);

        // Act
        discountService.applyAllDiscounts();
        Discount weekdayDiscount = discountService.createWeekdayDiscount();

        // Assert
        assertEquals(WeekdayDiscount.class, weekdayDiscount.getClass());
    }

    @Test
    void createWeekendDiscount_shouldCreateWeekendDiscount() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("8");
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order order = new Order(new Date(dateDTO), new OrderMenu(orderMenuRequest));
        when(orderRepository.findById(1L)).thenReturn(order);

        // Act
        discountService.applyAllDiscounts();
        Discount weekendDiscount = discountService.createWeekendDiscount();

        // Assert
        assertEquals(WeekendDiscount.class, weekendDiscount.getClass());
    }

    @Test
    void createSpecialDiscount_shouldCreateSpecialDiscount() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("25");
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order order = new Order(new Date(dateDTO), new OrderMenu(orderMenuRequest));
        when(orderRepository.findById(1L)).thenReturn(order);

        // Act
        discountService.applyAllDiscounts();
        Discount specialDiscount = discountService.createSpecialDiscount();

        // Assert
        assertEquals(SpecialDiscount.class, specialDiscount.getClass());
    }

    @Test
    void createGiftPriceDiscount_shouldCreateGiftPriceDiscount() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("25");
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order order = new Order(new Date(dateDTO), new OrderMenu(orderMenuRequest));
        when(orderRepository.findById(1L)).thenReturn(order);

        // Act
        discountService.applyAllDiscounts();
        Discount giftPriceDiscount = discountService.createGiftPriceDiscount();

        // Assert
        assertEquals(GiftPriceDiscount.class, giftPriceDiscount.getClass());
    }

    @Test
    void createQualifiedForGift_shouldReturnTrueForQualifiedTotalPrice() {
        // Arrange
        int qualifiedTotalPrice = DiscountConstant.GIFT_PRICE;

        // Act
        boolean qualifiedForGift = discountService.createQualifiedForGift(qualifiedTotalPrice);

        // Assert
        assertEquals(true, qualifiedForGift);
    }

    @Test
    void createQualifiedForGift_shouldReturnFalseForUnqualifiedTotalPrice() {
        // Arrange
        int unqualifiedTotalPrice = DiscountConstant.GIFT_PRICE - 1;

        // Act
        boolean qualifiedForGift = discountService.createQualifiedForGift(unqualifiedTotalPrice);

        // Assert
        assertEquals(false, qualifiedForGift);
    }

    @Test
    void calculateTotalPrice_shouldCalculateTotalPriceCorrectly() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("25");
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        OrderMenu orderMenu = new OrderMenu(orderMenuRequest);

        Order order = new Order(new Date(dateDTO), orderMenu);
        when(orderRepository.findById(1L)).thenReturn(order);

        // Act
        discountService.applyAllDiscounts();
        int totalPrice = discountService.calculateTotalPrice();

        // Assert
        assertEquals(1 * 55000 + 1 * 54000 + 2 * 15000 + 1 * 3000, totalPrice);
    }
}
