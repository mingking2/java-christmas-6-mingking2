package christmas.model.discount;

import christmas.constant.DiscountConstant;


public class WeekdayDiscount extends Discount {

    public WeekdayDiscount() {
        this.discountName = "평일 할인";
    }

    @Override
    public void calcuateDiscount(int date, int count) {
        discountPrice = count * DiscountConstant.DISCOUNT_WEEK_PRICE;
    }

}
