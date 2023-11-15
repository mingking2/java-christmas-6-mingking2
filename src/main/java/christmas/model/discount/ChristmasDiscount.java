package christmas.model.discount;

import christmas.constant.DiscountConstant;

public class ChristmasDiscount extends Discount {

    public ChristmasDiscount() {
        this.discountName = "크리스마스 디데이 할인";
    }


    @Override
    public void calcuateDiscount(int date, int count) {
        discountPrice = DiscountConstant.CHRISTMAS_BASIC_DISCOUNT_PRICE
                + DiscountConstant.CHRISTMAS_PER_DISCOUNT_PRICE * (date - 1);
    }

}
