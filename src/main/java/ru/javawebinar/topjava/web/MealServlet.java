package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {
    private final static List<MealTo> listMealsTo = getMealsTo();
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String forward;
        String action = request.getParameter("action");
        Integer mealId;
        if (action.equalsIgnoreCase("delete")) {
            mealId = Integer.parseInt(request.getParameter("mealId"));
            removeById(listMealsTo, mealId);
            request.setAttribute("meals", listMealsTo);
            forward = "/meals.jsp";
        } else if (action.equalsIgnoreCase("update")) {
            mealId = Integer.parseInt(request.getParameter("mealId"));
            MealTo mealTo = getById(listMealsTo, mealId);
            request.setAttribute("meal", mealTo);
            request.setAttribute("mealId", mealId);
            request.setAttribute("action", "update");
            forward = "/meal.jsp";
        } else if (action.equalsIgnoreCase("add")) {
            Meal mNew = new Meal(LocalDateTime.now(), "", 0);
            MealTo mToNew = new MealTo(mNew.getDateTime(), mNew.getDescription(), mNew.getCalories(), false, mNew.getId());
            request.setAttribute("meal", mToNew);
            request.setAttribute("mealId", mToNew.getId());
            request.setAttribute("action", "add");
            forward = "/meal.jsp";
        } else {
            request.setAttribute("meals", listMealsTo);
            forward = "/meals.jsp";
        }
        request.getRequestDispatcher(forward).
                forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("meals", listMealsTo);
        String forward = "/meals.jsp";
        String action = request.getParameter("action");
        LocalDateTime date = LocalDateTime.parse(request.getParameter("date"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Integer mealId = Integer.parseInt(request.getParameter("mealId"));
        if (action.equalsIgnoreCase("update")) {
            MealTo mToOld = getById(listMealsTo, mealId);
            MealTo mToNew = new MealTo(date, description, calories, mToOld.isExcess(), mToOld.getId());
            removeMeal(listMealsTo, mToOld);
            listMealsTo.add(mToNew);
        } else if (action.equalsIgnoreCase("add")) {
            listMealsTo.add(new MealTo(date, description, calories, false, mealId));
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }
}