package christmas.validator;

import christmas.constant.DateConstant;
import christmas.constant.ErrorConstant;
import christmas.constant.UtilConstant;
import christmas.util.Format;


public class DateValidator {

    public static void validateDate(String input) {
        validateNumber(input);
        validateDateRange(input);
    }

    private static void validateNumber(String input) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException(ErrorConstant.ERROR_NUMBER_NOT_INTEGER);
        }
    }

    private static boolean isNumeric(String input) {
        return input.matches(UtilConstant.NUMERIC_REGEX);
    }

    private static void validateDateRange(String input) {
        int date = Format.stringToInteger(input).getDate();
        if (date < DateConstant.MIN_DATE || date > DateConstant.MAX_DATE) {
            throw new IllegalArgumentException(ErrorConstant.ERROR_DATE);
        }
    }




}
