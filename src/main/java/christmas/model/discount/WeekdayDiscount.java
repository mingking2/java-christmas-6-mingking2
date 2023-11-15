package christmas.model.discount;

import christmas.constant.DiscountConstant;
import christmas.model.discount.Discount;
import java.text.NumberFormat;


public class WeekdayDiscount implements Discount {

    private int discountPrice;
    private String discountName;

    public WeekdayDiscount() {
        this.discountName = "평일 할인";
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
