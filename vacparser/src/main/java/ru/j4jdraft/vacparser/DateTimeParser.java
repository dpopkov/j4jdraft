package ru.j4jdraft.vacparser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class DateTimeParser {
    private static final int YEAR_PREFIX = LocalDate.now().getYear() / 100;
    private static final List<String> MONTHS = List.of(
            "янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"
    );

    public LocalDateTime parse(String dateTime) {
        String[] tokens = dateTime.split(", ");
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Illegal date time format: " + dateTime);
        }
        LocalDate date = parseDate(tokens[0]);
        LocalTime time = LocalTime.parse(tokens[1]);
        return LocalDateTime.of(date, time);
    }

    private LocalDate parseDate(String dateString) {
        String[] tokens = dateString.split(" ");
        if (tokens.length == 3) {
            int day = Integer.parseInt(tokens[0]);
            int month = parseMonth(tokens[1]);
            int year = Integer.parseInt(tokens[2]);
            year = YEAR_PREFIX * 100 + year;
            return LocalDate.of(year, month, day);
        } else if (tokens.length == 1) {
            if ("вчера".equals(tokens[0])) {
                return LocalDate.now().minusDays(1);
            } else if ("сегодня".equals(tokens[0])) {
                return LocalDate.now();
            } else {
                throw new IllegalArgumentException("Illegal date format: " + dateString);
            }
        }
        throw new IllegalArgumentException("Illegal date format: " + dateString);
    }


    private int parseMonth(String month) {
        int idx = MONTHS.indexOf(month);
        if (idx == -1) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        return idx + 1;
    }
}
