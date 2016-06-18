package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    UserMeal save(UserMeal meal);

    void delete(int userid, int id) throws NotFoundException;

    UserMeal get(int userid, int id) throws NotFoundException;


    List<UserMeal> getAll(int userid);

    UserMeal update(UserMeal meal);

    List<UserMealWithExceed> getFiltered(int id, LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate);
}
