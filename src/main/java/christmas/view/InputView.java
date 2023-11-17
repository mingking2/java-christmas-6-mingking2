package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.validator.DateValidator;
import christmas.validator.MenuValidator;

public class InputView {

    public static String readDate() {
        boolean checkException = true;
        String input ="";

        while (checkException) {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            input = Console.readLine();

            try {
                DateValidator.validateDate(input);
                checkException = false;
            } catch (IllegalArgumentException e) {
                ErrorView.inputDateOfVisitError();
            }
        }
        return input;
    }

    public static String readMenu() {
        boolean checkException = true;
        String input ="";

        while (checkException) {
            System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
            input = Console.readLine();

            try {
                MenuValidator.validateOrder(input);
                checkException = false;
            } catch (IllegalArgumentException e) {
                ErrorView.inputOrderMenu();
            }
        }
        return input;
    }

}
