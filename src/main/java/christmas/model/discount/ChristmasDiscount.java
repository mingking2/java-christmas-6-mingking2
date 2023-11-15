package christmas.model.discount;

import christmas.constant.DiscountConstant;
import java.text.NumberFormat;

public class ChristmasDiscount implements Discount {

    private int discountPrice;
    private String discountName;

    public ChristmasDiscount() {
        this.discountName = "크리스마스 디데이 할인";
    }

    @Override
    public int getDiscountPrice() {
        return discountPrice;
    }

    @Override
    public void calcuateDiscount(int date, int count) {
        discountPrice = DiscountConstant.CHRISTMAS_BASIC_DISCOUNT_PRICE
                + DiscountConstant.CHRISTMAS_PER_DISCOUNT_PRICE * (date - 1);
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        String formattedDiscountPrice = numberFormat.format(discountPrice);
        return discountName + ": -" + formattedDiscountPrice + "원";
    }
}
