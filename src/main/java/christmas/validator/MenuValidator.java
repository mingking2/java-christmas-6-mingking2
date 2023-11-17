package christmas.validator;

import christmas.constant.ErrorConstant;
import christmas.constant.MenuConstant;
import christmas.constant.UtilConstant;
import christmas.model.Menu;
import christmas.util.Format;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuValidator {
    public static void validateOrder(String orderInput) {
        validateOrderInput(orderInput);
        Map<Menu, Integer> orderMenus = Format.stringToMap(orderInput).getMenus();
        validateOrderMenus(orderMenus);
    }

    private static void validateOrderInput(String orderInput) {
        validateOrderInputForm(orderInput);
        validateDuplicateOrderMenu(orderInput);
    }

    private static void validateOrderMenus(Map<Menu, Integer> orderMenus) {
        validateMenusAndCounts(orderMenus);
        validateTitleDrink(orderMenus);
        validateMenuCount(orderMenus);
    }



    private static void validateOrderInputForm(String orderInput) {
        String orderPattern = MenuConstant.ORDER_PATTERN;

        Pattern pattern = Pattern.compile(orderPattern);
        Matcher matcher = pattern.matcher(orderInput);

        if(!matcher.matches()) {
            throw new IllegalArgumentException(ErrorConstant.ERROR_ORDER_MENU);
        }
    }

    private static void validateDuplicateOrderMenu(String orderInput) {
        Set<String> uniqueNames = new HashSet<>();

        Arrays.stream(orderInput.split(UtilConstant.SPLIT_STREAM_REGEX))
                .map(order -> order.split(UtilConstant.SPLIT_STREAM_MAP_REGEX))
                .forEach(parts -> {
                    String orderMenuName = parts[0];

                    if (!uniqueNames.add(orderMenuName)) {
                        throw new IllegalArgumentException(ErrorConstant.ERROR_ORDER_MENU);
                    }
                });
    }

    private static void validateMenusAndCounts(Map<Menu, Integer> orderMenus) {
        orderMenus.forEach((menu, count) -> validateMenuAndCount(menu, count));

    }

    private static void validateMenuAndCount(Menu orderMenu, int count) {
        String orderMenuName = orderMenu.getMenuName();
        boolean isValid = Arrays.stream(Menu.values())
                .anyMatch(menu -> orderMenuName.equals(menu.getMenuName()));

        if (!isValid || count < MenuConstant.MIN_COUNT) {
            throw new IllegalArgumentException(ErrorConstant.ERROR_ORDER_MENU);
        }

    }

    private static void validateTitleDrink(Map<Menu, Integer> orderMenus) {
        boolean allDrinks = orderMenus.keySet().stream()
                .allMatch(menu -> validateMenuTitle(menu));

        if (allDrinks) {
            throw new IllegalArgumentException(ErrorConstant.ERROR_ORDER_MENU_DRINK);
        }
    }


    private static boolean validateMenuTitle(Menu orderMenu) {
        String orderMenuTitle = MenuConstant.DRINK_TITLE;

        return orderMenuTitle.equals(orderMenu.getMenuTitle());
    }

    private static void validateMenuCount(Map<Menu, Integer> orderMenus) {
        int totalCount = orderMenus.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (totalCount > MenuConstant.MAX_COUNT) {
            throw new IllegalArgumentException(ErrorConstant.ERROR_ORDER_MENU_COUNT);
        }
    }

}
