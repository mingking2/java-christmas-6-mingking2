package christmas.constant;

public class ErrorConstant {
    public static final String ERROR_PREFIX = "[ERROR] ";
    public static final String ERROR_NUMBER_NOT_INTEGER = ERROR_PREFIX + "숫자만 입력해 주세요.";
    public static final String ERROR_DATE = ERROR_PREFIX + "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String ERROR_ORDER_MENU = ERROR_PREFIX + "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String ERROR_ORDER_MENU_DRINK = ERROR_PREFIX + "음료만 주문 시, 주문할 수 없습니다.";
    public static final String ERROR_ORDER_MENU_COUNT = ERROR_PREFIX + "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.";
}
