package christmas.service;

import christmas.constant.DiscountConstant;
import christmas.model.Order;
import christmas.model.discount.ChristmasDiscount;
import christmas.model.Date;
import christmas.model.discount.Discount;
import christmas.model.Menu;
import christmas.model.MenuType;
import christmas.model.OrderMenu;
import christmas.model.discount.GiftPriceDiscount;
import christmas.model.discount.SpecialDiscount;
import christmas.model.discount.WeekdayDiscount;
import christmas.model.discount.WeekendDiscount;
import christmas.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiscountService {

    private final OrderRepository orderRepository;
    private Order order;
    private List<Discount> discounts;

    public DiscountService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.discounts = new ArrayList<>();
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void applyAllDiscounts() {
        order = orderRepository.findById(1L);
        applyDiscount(createChristmasDiscount());
        applyDiscount(createWeekdayDiscount());
        applyDiscount(createWeekendDiscount());
        applyDiscount(createSpecialDiscount());
        applyDiscount(createGiftPriceDiscount());
    }

    public void applyDiscount(Discount discount) {
        if (discount != null) {
            discounts.add(discount);
        }
    }


    public Discount createChristmasDiscount() {
        Date date = order.getDate();

        if (date.isChristmasDay()) {
            Discount christmasDiscount = new ChristmasDiscount();
            christmasDiscount.calcuateDiscount(date.getDate(), 0);
            return christmasDiscount;
        }
        return null;
    }

    public Discount createWeekdayDiscount() {
        OrderMenu orderMenu = order.getOrderMenu();
        Date date = order.getDate();

        if (date.isWeekday()) {
            Discount weekdayDiscount = new WeekdayDiscount();
            int dessertCount = countTitleMenu(orderMenu, MenuType.DESSERT);
            weekdayDiscount.calcuateDiscount(date.getDate(), dessertCount);
            return weekdayDiscount;
        }
        return null;
    }

    public Discount createWeekendDiscount() {
        OrderMenu orderMenu = order.getOrderMenu();
        Date date = order.getDate();

        if (!date.isWeekday()) {
            Discount weekendDiscount = new WeekendDiscount();
            int mainCount = countTitleMenu(orderMenu, MenuType.MAIN);
            weekendDiscount.calcuateDiscount(date.getDate(), mainCount);
            return weekendDiscount;
        }
        return null;
    }

    public int countTitleMenu(OrderMenu orderMenu, MenuType menuType) {
        Map<Menu, Integer> menus = orderMenu.getMenus();
        return menus.entrySet().stream()
                .filter(entry -> entry.getKey().getMenuType() == menuType)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public Discount createSpecialDiscount() {
        Date date = order.getDate();

        if (date.isSpecial()) {
            Discount specialDiscount = new SpecialDiscount();
            specialDiscount.calcuateDiscount(date.getDate(), 0);
            return specialDiscount;
        }
        return null;
    }

    public Discount createGiftPriceDiscount() {
        int totalPriceBeforeDiscounts = calculateTotalPrice();
        if (createQualifiedForGift(totalPriceBeforeDiscounts)) {
            Discount giftPriceDiscount = new GiftPriceDiscount();
            giftPriceDiscount.calcuateDiscount(0, DiscountConstant.BONUS_GIFT_PRICE);
            return giftPriceDiscount;
        }
        return null;
    }


    public boolean createQualifiedForGift(int totalPriceBeforeDiscounts) {
        return totalPriceBeforeDiscounts >= DiscountConstant.GIFT_PRICE;
    }

    public int calcualteGiftPrice(int totalPriceBeforeDiscounts) {
        if (createQualifiedForGift(totalPriceBeforeDiscounts)) {
            return DiscountConstant.BONUS_GIFT_PRICE;
        }
        return 0;
    }

    public int calculateTotalPrice() {
        OrderMenu orderMenu = order.getOrderMenu();
        return orderMenu.getMenus().entrySet().stream()
                .mapToInt(entry -> entry.getKey().getMenuPrice() * entry.getValue())
                .sum();
    }

    public int calculateTotalDiscount() {
        int totalDiscount = 0;

        for (Discount discount : discounts) {
            totalDiscount += discount.getDiscountPrice();
        }
        return totalDiscount;
    }


}
