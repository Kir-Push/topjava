package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(UserMeal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int userid, int id) throws NotFoundException {

         ExceptionUtil.checkNotFoundWithId(repository.delete(userid,id),id);
    }

    @Override
    public UserMeal get(int userid, int id) throws NotFoundException {
      return  ExceptionUtil.checkNotFoundWithId(repository.get(userid,id),id);

    }

    @Override
    public List<UserMeal> getAll(int userid) {
        return repository.getAll(userid);
    }

    @Override
    public UserMeal update(UserMeal meal) {
       return save(meal);
    }

    @Override
    public List<UserMealWithExceed> getFiltered(int id, LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate) {
        List<UserMeal> mealList = repository.getFilteredByDate(id,startDate, endDate);
        return UserMealsUtil.getFilteredWithExceeded(mealList,startTime,endTime,UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}
