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
    private final DiscountService discountService;

    public OrderController(OrderService orderService, DiscountService discountService) {
        this.orderService = orderService;
        this.discountService = discountService;
    }



    public void run() {
        inputDateAndOrderMenu();
        applyDiscounts();
        printResult();
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

    public void applyDiscounts() {
        discountService.applyAllDiscounts();
    }


    public void printResult() {
        printTotalPriceBeforeDiscounts();
        printBonusMenu();
        printBenefits();
        printTotalBenefits();
        printTotalPriceAfterDiscounts();
    }

    public void printTotalPriceBeforeDiscounts() {
        int totalPriceBeforeDiscounts = discountService.calculateTotalPrice();
        OutputView.printBeforeSalePrice(totalPriceBeforeDiscounts);
    }

    public void printBonusMenu() {
        boolean hasGiftPriceDiscount = discountService.createGiftPriceDiscount() != null;
        OutputView.printBonusMenu(hasGiftPriceDiscount);
    }

    public void printBenefits() {
        OutputView.printBenefit(discountService.getDiscounts());
    }

    public void printTotalBenefits() {
        int totalDiscount = discountService.calculateTotalDiscount();
        OutputView.printTotalBenefitPrice(totalDiscount);
    }

    public void printTotalPriceAfterDiscounts() {
        int totalPriceBeforeDiscounts = discountService.calculateTotalPrice();
        int giftPrice = discountService.calcualteGiftPrice(totalPriceBeforeDiscounts);
        int totalDiscount = discountService.calculateTotalDiscount();
        int totalPriceAfterDiscounts = totalPriceBeforeDiscounts - totalDiscount + giftPrice;
        OutputView.printAfterSalePrice(totalPriceAfterDiscounts);
        OutputView.printEventBadge(totalDiscount);
    }




}
