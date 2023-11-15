package christmas.view;

import christmas.dto.DateDTO;
import christmas.dto.OrderMenuRequest;
import christmas.model.discount.Discount;
import christmas.model.Menu;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printEventMessage(DateDTO dateDTO) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", dateDTO.getDate());
    }

    public static void printMenu(OrderMenuRequest orderMenuRequest) {
        Map<Menu, Integer> orderMenus = orderMenuRequest.getMenus();
        System.out.println("\n<주문 메뉴>");

        orderMenus.forEach((menu, count) -> System.out.println(menu.getMenuName() + " " + count + "개"));
    }

    public static void printBeforeSalePrice(int beforeSalePrice) {
        String formattedDiscountPrice = NumberFormat.getInstance().format(beforeSalePrice);
        System.out.printf("\n<할인 전 총주문 금액>\n%s원%n", formattedDiscountPrice);

    }

    public static void printBonusMenu(boolean gift) {
        System.out.println("\n<증정 메뉴>");
        String giftName = "없음";
        if (gift) {
            giftName = "샴페인 1개";
        }
        System.out.println(giftName);
    }

    public static void printBenefit(List<Discount> discounts) {
        System.out.println("\n<혜택 내역>");

        discounts.stream()
                .filter(discount -> discount.getDiscountPrice() != 0)
                .forEach(System.out::println);

        if (discounts.stream().noneMatch(discount -> discount.getDiscountPrice() != 0)) {
            System.out.println("없음");
        }
    }

    public static void printTotalBenefitPrice(int totalBenefitPrice) {
        String formattedDiscountPrice = NumberFormat.getInstance().format(totalBenefitPrice);
        System.out.println("\n<총혜택 금액>");
        if (totalBenefitPrice != 0) {
            System.out.print("-");
        }
        System.out.println(formattedDiscountPrice + "원");
    }

    public static void printAfterSalePrice(int afterSalePrice) {
        String formattedDiscountPrice = NumberFormat.getInstance().format(afterSalePrice);
        System.out.printf("\n<할인 후 예상 결제 금액>\n%s원%n", formattedDiscountPrice);
    }

    public static void printEventBadge(int totalBenefitPrice) {
        System.out.println("\n<12월 이벤트 배지>");

        String[] badges = {"없음", "별", "트리", "산타"};
        int[] thresholds = {0, 5000, 10000, 20000};

        String eventBadge = badges[0];

        for (int i = thresholds.length - 1; i >= 0; i--) {
            if (totalBenefitPrice >= thresholds[i]) {
                eventBadge = badges[i];
                break;
            }
        }

        System.out.println(eventBadge);
    }
}
