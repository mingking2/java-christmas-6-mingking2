package christmas.model.discount;

import christmas.constant.DiscountConstant;

public class SpecialDiscount extends Discount {

    public SpecialDiscount() {
        this.discountName = "특별 할인";
    }

    @Override
    public void calcuateDiscount(int date, int count) {
        discountPrice = DiscountConstant.DISCOUNT_SPECIAL_PRICE;
    }

}
