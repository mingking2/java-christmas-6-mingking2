package christmas.model;

public class Order {

    private Long id;
    private final Date date;
    private final OrderMenu orderMenu;
    private final int totalPrice;

    public Order(Date date, OrderMenu orderMenu) {
        this.date = date;
        this.orderMenu = orderMenu;
        this.totalPrice = calculateTotalPrice();
    }

    public Date getDate() {
        return date;
    }

    public OrderMenu getOrderMenu() {
        return orderMenu;
    }

    public int getTotalPrice() { return totalPrice; }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int calculateTotalPrice() {
        return orderMenu.getMenus().entrySet().stream()
                .mapToInt(entry -> entry.getKey().getMenuPrice() * entry.getValue())
                .sum();
    }
}
