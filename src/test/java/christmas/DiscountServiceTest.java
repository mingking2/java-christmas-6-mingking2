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
        orderRepository = new OrderRepository();
        discountService = new DiscountService(orderRepository);
        orderRepository.clear();
    }

    @Test
    void applyAllDiscounts_shouldApplyAllDiscounts() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("3");
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order order = new Order(new Date(dateDTO), new OrderMenu(orderMenuRequest));
        orderRepository.save(order);

        // Act
        discountService.applyAllDiscounts(order.getId());

        // Assert
        assertEquals(4, discountService.getDiscounts().size());
    }

    @Test
    void createChristmasDiscount_shouldCreateChristmasDiscount() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("25");
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order order = new Order(new Date(dateDTO), new OrderMenu(orderMenuRequest));
        orderRepository.save(order);

        Order bringOrder = orderRepository.findById(order.getId());

        // Act
        Discount christmasDiscount = discountService.createChristmasDiscount(bringOrder.getDate());

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
        orderRepository.save(order);

        Order bringOrder = orderRepository.findById(order.getId());

        // Act
        Discount weekdayDiscount = discountService.createWeekdayDiscount(bringOrder.getDate(), bringOrder.getOrderMenu());

        // Assert
        assertEquals(WeekdayDiscount.class, weekdayDiscount.getClass());
    }

    @Test
    void createWeekendDiscount_shouldCreateWeekendDiscount() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("8");
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order order = new Order(new Date(dateDTO), new OrderMenu(orderMenuRequest));
        orderRepository.save(order);

        Order bringOrder = orderRepository.findById(order.getId());

        // Act
        Discount weekendDiscount = discountService.createWeekendDiscount(bringOrder.getDate(), bringOrder.getOrderMenu());

        // Assert
        assertEquals(WeekendDiscount.class, weekendDiscount.getClass());
    }

    @Test
    void createSpecialDiscount_shouldCreateSpecialDiscount() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("25");
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order order = new Order(new Date(dateDTO), new OrderMenu(orderMenuRequest));
        orderRepository.save(order);

        Order bringOrder = orderRepository.findById(order.getId());
        // Act
        Discount specialDiscount = discountService.createSpecialDiscount(bringOrder.getDate());

        // Assert
        assertEquals(SpecialDiscount.class, specialDiscount.getClass());
    }

    @Test
    void createGiftPriceDiscount_shouldCreateGiftPriceDiscount() {
        // Arrange
        DateDTO dateDTO = Format.stringToInteger("25");
        OrderMenuRequest orderMenuRequest = Format.stringToMap("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order order = new Order(new Date(dateDTO), new OrderMenu(orderMenuRequest));
        orderRepository.save(order);

        Order bringOrder = orderRepository.findById(order.getId());
        // Act
        Discount giftPriceDiscount = discountService.createGiftPriceDiscount(bringOrder.getTotalPrice());

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

}
