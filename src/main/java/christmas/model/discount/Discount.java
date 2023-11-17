package christmas.model.discount;


import java.text.NumberFormat;

public abstract class Discount {
    protected int discountPrice;
    protected String discountName;

    abstract public void calcuateDiscount(int date, int count);
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        String formattedDiscountPrice = numberFormat.format(discountPrice);
        return discountName + ": -" + formattedDiscountPrice + "원";
    };
    public int getDiscountPrice() {
        return discountPrice;
    };

}
