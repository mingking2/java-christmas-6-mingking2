package christmas.model.discount;

import christmas.constant.DiscountConstant;

public class WeekendDiscount extends Discount {

    public WeekendDiscount() {
        this.discountName = "주말 할인";
    }

    @Override
    public void calcuateDiscount(int date, int count) {
        discountPrice = count * DiscountConstant.DISCOUNT_WEEK_PRICE;
    }
}
