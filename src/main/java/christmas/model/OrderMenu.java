package christmas.model;

import christmas.dto.OrderMenuRequest;
import java.util.Map;

public class OrderMenu {

    private final Map<Menu, Integer> menus;

    public OrderMenu(OrderMenuRequest orderMenuRequest) {
        this.menus = orderMenuRequest.getMenus();
    }

    public Map<Menu, Integer> getMenus() {
        return menus;
    }
}
