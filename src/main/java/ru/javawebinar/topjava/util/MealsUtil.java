package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealsUtil {

    public static final MealsUtil mUtil = new MealsUtil();
    private final static List<Meal> meals = Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );

    public static MealTo getMealTo(List<MealTo> list, MealTo mealTo) {
        for (MealTo m : list) {
            if (m.equals(mealTo)) return m;
        }
        return null;
    }

    public static MealTo getById(List<MealTo> list, Integer id) {
        for (MealTo m : list) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }

    public static boolean removeMeal(List<MealTo> list, MealTo mealTo) {
        for (MealTo m : list) {
            if (m.getId().equals(mealTo.getId())) {
                list.remove(mealTo);
                return true;
            }
        }
        return false;
    }

    public static boolean removeById(List<MealTo> list, Integer id) {
        for (MealTo m : list) {
            if (m.getId().equals(id)) list.remove(m);
            return true;
        }
        return false;
    }

    public static List<Meal> getMeals() {
        return meals;
    }

    public static void main(String[] args) {


        List<MealTo> mealsTo = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);
    }

    public static List<MealTo> filteredByStreams(List<Meal> meals, LocalTime startTime, LocalTime endTime, final int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static MealTo createTo(Meal meal, boolean excess) {
        return new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess, meal.getId());
    }

    public static List<MealTo> getMealsTo() {
        return filteredByStreams(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
    }
}
