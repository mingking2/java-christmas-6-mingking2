package christmas.model.discount;

import christmas.constant.DiscountConstant;
import java.text.NumberFormat;

public class WeekendDiscount implements Discount {

    private int discountPrice;
    private String discountName;

    public WeekendDiscount() {
        this.discountName = "주말 할인";
    }

    @Override
    public int getDiscountPrice() {
        return discountPrice;
    }

    @Override
    public void calcuateDiscount(int date, int count) {
        discountPrice = count * DiscountConstant.DISCOUNT_WEEK_PRICE;
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        String formattedDiscountPrice = numberFormat.format(discountPrice);
        return discountName + ": -" + formattedDiscountPrice + "원";
    }
}
