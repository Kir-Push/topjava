package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by buhalo on 05.06.16.
 */
public class DaoMealInMemory implements Dao {

    private Map<Integer,UserMeal> meals;

    private static AtomicInteger id = new AtomicInteger(0);

    private static DaoMealInMemory ourInstance = new DaoMealInMemory();

    public static DaoMealInMemory getInstance() {
        return ourInstance;
    }

    private DaoMealInMemory() {
       meals = new ConcurrentHashMap<>();
        id = new AtomicInteger(0);

        create(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
       create(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
       create(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
       create(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
       create(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
       create(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public void create(UserMeal userMeal) {
        userMeal.setId(id.get());
        meals.put(id.getAndIncrement(),userMeal);
    }

    @Override
    public UserMeal read(int id) {
        return meals.get(id);
    }

    @Override
    public List<UserMeal> read(LocalDateTime localDateTime) {
        return meals.values().stream().filter(em -> em.getDateTime().equals(localDateTime)).collect(Collectors.toList());
    }

    @Override
    public List<UserMeal> read(String description) {
        return meals.values().stream().filter(em -> em.getDescription().equals(description)).collect(Collectors.toList());
    }

    @Override
    public List<UserMeal> findAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public void update(UserMeal userMeal) {
        meals.put(userMeal.getId(),userMeal);
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }
}
