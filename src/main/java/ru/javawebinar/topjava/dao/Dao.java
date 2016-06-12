package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Created by buhalo on 05.06.16.
 */
public interface Dao {
    void create(UserMeal userMeal);

    UserMeal read(int id);

    List<UserMeal> read(LocalDateTime localDateTime);

    List<UserMeal> read(String description);


    List<UserMeal> findAll();

    void update(UserMeal userMeal);

    void delete(int id);


}
