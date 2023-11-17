package christmas.dto;

public class OrderResponse {
    private Long id;
    private int totalPrice;

    public OrderResponse(Long id, int totalPrice) {
        this.id = id;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
