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
    private List<Discount> discounts;

    public DiscountService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.discounts = new ArrayList<>();
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void applyAllDiscounts(Long OrderId) {
        Order order = orderRepository.findById(OrderId);
        Date date = order.getDate();
        OrderMenu orderMenu = order.getOrderMenu();
        int totalPrice = order.getTotalPrice();

        applyDiscount(createChristmasDiscount(date));
        applyDiscount(createWeekdayDiscount(date, orderMenu));
        applyDiscount(createWeekendDiscount(date, orderMenu));
        applyDiscount(createSpecialDiscount(date));
        applyDiscount(createGiftPriceDiscount(totalPrice));
    }

    public void applyDiscount(Discount discount) {
        if (discount != null) {
            discounts.add(discount);
        }
    }


    public Discount createChristmasDiscount(Date date) {
            if (date.isChristmasDay()) {
                Discount christmasDiscount = new ChristmasDiscount();
                christmasDiscount.calcuateDiscount(date.getDate(), 0);
                return christmasDiscount;
            }
            return null;
    }

    public Discount createWeekdayDiscount(Date date, OrderMenu orderMenu) {
        if (date.isWeekday()) {
            Discount weekdayDiscount = new WeekdayDiscount();
            int dessertCount = countTitleMenu(orderMenu, MenuType.DESSERT);
            weekdayDiscount.calcuateDiscount(date.getDate(), dessertCount);
            return weekdayDiscount;
        }
        return null;
    }

    public Discount createWeekendDiscount(Date date, OrderMenu orderMenu) {
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

    public Discount createSpecialDiscount(Date date) {
        if (date.isSpecial()) {
            Discount specialDiscount = new SpecialDiscount();
            specialDiscount.calcuateDiscount(date.getDate(), 0);
            return specialDiscount;
        }
        return null;
    }

    public Discount createGiftPriceDiscount(int totalPrice) {
        if (createQualifiedForGift(totalPrice)) {
            Discount giftPriceDiscount = new GiftPriceDiscount();
            giftPriceDiscount.calcuateDiscount(0, DiscountConstant.BONUS_GIFT_PRICE);
            return giftPriceDiscount;
        }
        return null;
    }


    public boolean createQualifiedForGift(int totalPriceBeforeDiscounts) {
        return totalPriceBeforeDiscounts >= DiscountConstant.GIFT_PRICE;
    }

    public int calculateGiftPrice(int totalPriceBeforeDiscounts) {
        if (createQualifiedForGift(totalPriceBeforeDiscounts)) {
            return DiscountConstant.BONUS_GIFT_PRICE;
        }
        return 0;
    }

    public int calculateTotalDiscount() {
        int totalDiscount = 0;

        for (Discount discount : discounts) {
            totalDiscount += discount.getDiscountPrice();
        }
        return totalDiscount;
    }


}
