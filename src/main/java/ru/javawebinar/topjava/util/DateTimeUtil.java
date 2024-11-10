package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static boolean isDateInclusive(LocalDate date, String startDate, String endDate) {
        LocalDate start = toLocalDate(startDate);
        LocalDate end = toLocalDate(endDate);
        return date.compareTo(start) >= 0 && date.compareTo(end) <= 0;
    }

    public static boolean isTimeInclusive(LocalTime time, String startTime, String endTime) {
        LocalTime start = toLocalTime(startTime);
        LocalTime end = toLocalTime(endTime);
        return time.compareTo(start) >= 0 && time.compareTo(end) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate toLocalDate(String date) {
        if (date == null || date.isEmpty()) {
            throw new IllegalArgumentException("Строка даты не может быть пустой или равной null");
        }
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    public static LocalTime toLocalTime(String time) {
        if (time == null || time.isEmpty()) {
            throw new IllegalArgumentException("Строка даты не может быть пустой или равной null");
        }
        return LocalTime.parse(time, TIME_FORMATTER);
    }
}

