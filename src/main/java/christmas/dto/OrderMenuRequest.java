package christmas.dto;

import christmas.model.Menu;
import java.util.Map;

public class OrderMenuRequest {
    private Map<Menu, Integer> menus;

    public OrderMenuRequest(Map<Menu, Integer> menus) {
        this.menus = menus;
    }

    public Map<Menu, Integer> getMenus() {
        return menus;
    }
}
