package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.DaoMealInMemory;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by buhalo on 05.06.16.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private static final Dao dao = DaoMealInMemory.getInstance();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action != null && action.equals("edit"))
        {
            LOG.debug("redirect to mealEdit");
            String id = request.getParameter("id");
            request.setAttribute("meal",dao.read(Integer.parseInt(id)));
            request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
        }
        else if(action != null && action.equals("delete"))
        {
            String id = request.getParameter("id");
            dao.delete(Integer.parseInt(id));
            action = null;

        }

        if(action == null) {
            LOG.debug("redirect to mealList");
            List<UserMealWithExceed> meals = UserMealsUtil.getFilteredWithExceeded(dao.findAll(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
            request.setAttribute("meals", meals);
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("doPost edit new meal");
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String date = req.getParameter("Date");
        String description = req.getParameter("description");
        String calories = req.getParameter("calories");
        LocalDateTime loc =LocalDateTime.from(TimeUtil.formatter.parse(date));
        UserMeal meal = dao.read(Integer.parseInt(id));
        meal.setCalories(Integer.parseInt(calories));
        meal.setDateTime(loc);
        meal.setDescription(description);
        dao.update(meal);
        List<UserMealWithExceed> meals = UserMealsUtil.getFilteredWithExceeded(dao.findAll(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
        req.setAttribute("meals", meals);
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);

    }
}
