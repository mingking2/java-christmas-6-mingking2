package christmas;

import christmas.controller.OrderController;
import christmas.repository.OrderRepository;
import christmas.service.DiscountService;
import christmas.service.OrderService;

public class Application {
    public static void main(String[] args) {
        OrderService orderService = new OrderService(OrderRepository.getInstance());
        DiscountService discountService = new DiscountService(OrderRepository.getInstance());

        OrderController orderController = new OrderController(orderService, discountService);

        orderController.run();
    }
}
