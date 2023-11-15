package christmas.view;

import christmas.dto.DateDTO;
import christmas.dto.OrderMenuRequest;
import christmas.model.Menu;
import java.util.Map;

public class OutputView {

    public static void printEventMessage(DateDTO dateDTO) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", dateDTO.getDate());
    }

    public static void printMenu(OrderMenuRequest orderMenuRequest) {
        Map<Menu, Integer> orderMenus = orderMenuRequest.getMenus();
        System.out.println("\n<주문 메뉴>");

        orderMenus.forEach((menu, count) -> System.out.println(menu.getMenuName() + " " + count + "개"));
    }


}
