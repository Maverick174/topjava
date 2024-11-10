package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

@Controller
public class MealRestController {
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);
    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id) {
        log.info("Getting meal with id={}", id);
        return service.get(id);
    }

    public List<Meal> getAll() {
        log.info("Getting all meals");
        return service.getAll();
    }

    public void save(Meal meal) {
        log.info("Saving meal {}", meal);
        service.create(meal);
    }

    public void delete(int id) {
        log.info("Deleting meal with id={}", id);
        service.delete(id);
    }
}