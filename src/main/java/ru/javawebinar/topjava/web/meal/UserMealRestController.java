package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    @Autowired
    private UserMealService service;

    public List<UserMeal> getAll()
    {
        return service.getAll(LoggedUser.id());
    }

    public UserMeal get(int id)
    {
        return service.get(LoggedUser.id(),id);
    }

    public UserMeal create(UserMeal userMeal)
    {
        userMeal.setUserId(LoggedUser.id());
      return service.save(userMeal);
    }

    public UserMeal update(UserMeal userMeal)
    {
      return create(userMeal);
    }

    public void delete(int id)
    {
        service.delete(LoggedUser.id(),id);
    }

    public List<UserMealWithExceed> getFiltered(String timeBefore, String  endTime,String  startDate, String  endDate) {
        LocalTime locTimeBefore = (timeBefore != null && !timeBefore.equals("")) ? LocalTime.parse(timeBefore) : LocalTime.MIN;
        LocalTime locTimeAfter =  (endTime != null && !endTime.equals("")) ? LocalTime.parse(endTime) : LocalTime.MAX;;
        LocalDate locDateBefore = (startDate != null && !startDate.equals("")) ? LocalDate.parse(startDate) : LocalDate.MIN;
        LocalDate locDateAfter = (endDate != null && !endDate.equals("")) ? LocalDate.parse(endDate) : LocalDate.MAX;;

        return service.getFiltered(LoggedUser.id(),locTimeBefore,locTimeAfter, locDateBefore, locDateAfter).stream()
            .sorted((m,m2)->m2.getDateTime().compareTo(m.getDateTime())).collect(Collectors.toList());}

}
