package christmas.model;

public class Order {

    private Long id;
    private final Date date;
    private final OrderMenu orderMenu;

    public Order(Date date, OrderMenu orderMenu) {
        this.date = date;
        this.orderMenu = orderMenu;
    }

    public Date getDate() {
        return date;
    }

    public OrderMenu getOrderMenu() {
        return orderMenu;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
