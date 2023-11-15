package christmas.model;

import christmas.constant.DateConstant;
import christmas.dto.DateDTO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Date {

    private final int date;
    private final boolean christmasDay;
    private final boolean weekday;
    private final boolean special;

    public Date(DateDTO dateDTO) {
        date = dateDTO.getDate();
        this.christmasDay = checkChristmasDate();
        this.weekday = checkWeekdayDate();
        this.special = checkSpecialDate();
    }

    public int getDate() {
        return date;
    }

    public boolean isChristmasDay() {
        return christmasDay;
    }

    public boolean isWeekday() {
        return weekday;
    }

    public boolean isSpecial() {
        return special;
    }

    public boolean checkChristmasDate() {
        if (date <= DateConstant.CHRISTMAS_DATE) {
            return true;
        }
        return false;
    }

    public boolean checkWeekdayDate() {
        LocalDate inputDate = LocalDate.of(2023, 12, date);

        DayOfWeek dayOfWeek = inputDate.getDayOfWeek();
        return !dayOfWeek.equals(DayOfWeek.FRIDAY) && !dayOfWeek.equals(DayOfWeek.SATURDAY);
    }

    public boolean checkSpecialDate() {
        List<Integer> specialDates = Arrays.asList(3, 10, 17, 24, 25, 31);
        if (specialDates.contains(date)) {
            return true;
        }
        return false;
    }


}
