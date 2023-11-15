package christmas.controller;

import christmas.dto.DateDTO;
import christmas.dto.OrderMenuRequest;
import christmas.repository.OrderRepository;
import christmas.service.DiscountService;
import christmas.service.OrderService;
import christmas.util.Format;
import christmas.view.InputView;
import christmas.view.OutputView;

public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }



    public void run() {
        inputDateAndOrderMenu();
    }

    public void inputDateAndOrderMenu() {
        String inputDate = InputView.readDate();
        DateDTO dateDTO = Format.stringToInteger(inputDate);

        String inputOrderMenu = InputView.readMenu();
        OrderMenuRequest orderMenuRequest = Format.stringToMap(inputOrderMenu);

        orderService.createOrder(dateDTO, orderMenuRequest);

        OutputView.printEventMessage(dateDTO);
        OutputView.printMenu(orderMenuRequest);
    }

}
