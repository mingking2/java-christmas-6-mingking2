package christmas.model.discount;



public interface Discount {

    void calcuateDiscount(int date, int count);
    String toString();
    int getDiscountPrice();

}
