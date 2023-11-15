package christmas.controller;

import christmas.dto.DateDTO;
import christmas.dto.OrderMenuRequest;
import christmas.dto.OrderResponse;
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
        OrderResponse orderResponse = inputDateAndOrderMenu();
        applyDiscounts(orderResponse.getId());
        printResult(orderResponse.getTotalPrice());
    }

    public OrderResponse inputDateAndOrderMenu() {
        String inputDate = InputView.readDate();
        DateDTO dateDTO = Format.stringToInteger(inputDate);

        String inputOrderMenu = InputView.readMenu();
        OrderMenuRequest orderMenuRequest = Format.stringToMap(inputOrderMenu);

        OrderResponse orderResponse = orderService.createOrder(dateDTO, orderMenuRequest);

        OutputView.printEventMessage(dateDTO);
        OutputView.printMenu(orderMenuRequest);

        return orderResponse;
    }

    public void applyDiscounts(Long OrderId) {
        discountService.applyAllDiscounts(OrderId);
    }


    public void printResult(int totalPrice) {
        printTotalPriceBeforeDiscounts(totalPrice);
        printBonusMenu(totalPrice);
        printBenefits();
        printTotalBenefits();
        printTotalPriceAfterDiscounts(totalPrice);
    }

    public void printTotalPriceBeforeDiscounts(int totalPrice) {
        OutputView.printBeforeSalePrice(totalPrice);
    }

    public void printBonusMenu(int totalPrice) {
        boolean hasGiftPriceDiscount = discountService.createGiftPriceDiscount(totalPrice) != null;
        OutputView.printBonusMenu(hasGiftPriceDiscount);
    }

    public void printBenefits() {
        OutputView.printBenefit(discountService.getDiscounts());
    }

    public void printTotalBenefits() {
        int totalDiscount = discountService.calculateTotalDiscount();
        OutputView.printTotalBenefitPrice(totalDiscount);
    }

    public void printTotalPriceAfterDiscounts(int totalPrice) {
        int giftPrice = discountService.calculateGiftPrice(totalPrice);
        int totalDiscount = discountService.calculateTotalDiscount();
        int totalPriceAfterDiscounts = totalPrice - totalDiscount + giftPrice;
        OutputView.printAfterSalePrice(totalPriceAfterDiscounts);
        OutputView.printEventBadge(totalDiscount);
    }




}
