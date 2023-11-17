package christmas.util;

import christmas.constant.UtilConstant;
import christmas.dto.DateDTO;
import christmas.dto.OrderMenuRequest;
import christmas.model.Menu;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Format {

    public static DateDTO stringToInteger(String input) {
        return new DateDTO(Integer.parseInt(input));
    }

    public static OrderMenuRequest stringToMap(String orderInput) {
        return new OrderMenuRequest(Arrays.stream(orderInput.split(UtilConstant.SPLIT_STREAM_REGEX))
                .map(order -> order.split(UtilConstant.SPLIT_STREAM_MAP_REGEX))
                .collect(Collectors.toMap(
                        parts -> Menu.valueOf(parts[0]),
                        parts -> Integer.parseInt(parts[1])
                )));
    }
}
