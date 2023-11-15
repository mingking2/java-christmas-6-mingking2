package christmas.model.discount;


public class GiftPriceDiscount extends Discount{


    private int discountPrice;
    private String discountName;

    public GiftPriceDiscount() {
        this.discountName = "증정 이벤트";
    }


    @Override
    public void calcuateDiscount(int date, int count) {
        discountPrice = count;
    }



}
