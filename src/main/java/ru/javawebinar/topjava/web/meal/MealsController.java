package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by buhalo on 10.07.16.
 */
@Controller
@RequestMapping(value = "meals")
public class MealsController {
    @Autowired
    UserMealService service;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(HttpServletRequest request)
    {
        int userId = AuthorizedUser.id();
        List<UserMealWithExceed> meals = UserMealsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay());
        request.setAttribute("mealList",meals);
        return "mealList";

    }

    @RequestMapping(params = {"action=delete","id"},method = RequestMethod.GET)
    public String delete(HttpServletRequest request)
    {
        int mealId = Integer.parseInt(request.getParameter("id"));
        service.delete(mealId,AuthorizedUser.id());
        return "redirect:meals";
    }

    @RequestMapping(params = {"action=update","id"},method = RequestMethod.GET)
    public String update(HttpServletRequest request)
    {
        int mealId = Integer.parseInt(request.getParameter("id"));
        UserMeal userMeal = service.get(mealId,AuthorizedUser.id());
        request.setAttribute("meal",userMeal);
        return "mealEdit";
    }

    @RequestMapping(params = {"action=create"},method = RequestMethod.GET)
    public String create(HttpServletRequest request)
    {
        UserMeal userMeal  = new UserMeal(LocalDateTime.now().withNano(0).withSecond(0), "", 1000);
        request.setAttribute("meal",userMeal);
        return "mealEdit";
    }

    @RequestMapping(params = {"!action"},method = RequestMethod.POST)
    public String save(HttpServletRequest request)
    {
        final UserMeal userMeal = new UserMeal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        if (request.getParameter("id").isEmpty()) {
            userMeal.setId(null);
            service.save(userMeal,AuthorizedUser.id());
        } else {
            userMeal.setId(Integer.parseInt(request.getParameter("id")));
           service.update(userMeal,AuthorizedUser.id());
        }
        return "redirect:meals";
    }

    @RequestMapping(params = {"action=filter"},method = RequestMethod.POST)
    public String filter(HttpServletRequest request)
    {
        LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request));
        LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request));
        LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request));
        LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request));
        request.setAttribute("mealList",UserMealsUtil.getFilteredWithExceeded(
            service.getBetweenDates(
                    startDate != null ? startDate : TimeUtil.MIN_DATE, endDate != null ? endDate : TimeUtil.MAX_DATE, AuthorizedUser.id()),
            startTime != null ? startTime : LocalTime.MIN, endTime != null ? endTime : LocalTime.MAX, AuthorizedUser.getCaloriesPerDay()
    ));
        return "mealList";
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }

}
