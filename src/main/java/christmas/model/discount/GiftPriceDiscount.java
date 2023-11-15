package christmas.model.discount;

import java.text.NumberFormat;

public class GiftPriceDiscount implements Discount{


    private int discountPrice;
    private String discountName;

    public GiftPriceDiscount() {
        this.discountName = "증정 이벤트";
    }

    @Override
    public int getDiscountPrice() {
        return discountPrice;
    }

    @Override
    public void calcuateDiscount(int date, int count) {
        discountPrice = count;
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        String formattedDiscountPrice = numberFormat.format(discountPrice);
        return discountName + ": -" + formattedDiscountPrice + "원";
    }

}
