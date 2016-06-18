package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal);

    boolean delete(int userid,int id);

    UserMeal get(int userid,int id);

    List<UserMeal> getAll(int userid);

    List<UserMeal> getFilteredByDate(int id, LocalDate startDate, LocalDate endDate);
}
