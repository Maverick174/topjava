package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final ConcurrentHashMap<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public List<Meal> getAll() {
        return repository.values().stream().filter(meal -> meal.getUserId() == SecurityUtil.getAuthUserId()).collect(Collectors.toList());
    }

    {
        MealsUtil.meals.forEach(meal -> save(meal));
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(SecurityUtil.getAuthUserId());
        } else {
            if (!repository.containsKey(meal.getId()) || repository.get(meal.getId()).getUserId() != SecurityUtil.getAuthUserId()) {
                throw new IllegalArgumentException("Еда не найдена или не принадлежит пользователю");
            }
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id) {
        Meal meal = repository.get(id);
        if (meal != null && meal.getUserId() == SecurityUtil.getAuthUserId()) {
            repository.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id) {
        Meal meal = repository.get(id);
        return (meal != null && meal.getUserId() == SecurityUtil.getAuthUserId()) ? meal : null;
    }
}