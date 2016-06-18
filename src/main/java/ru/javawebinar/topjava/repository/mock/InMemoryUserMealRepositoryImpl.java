package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.DateUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, Map<Integer,UserMeal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(this::save);
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        if(userMeal.getUserId() == null)
        {
            userMeal.setUserId(-1);
        }
        Map<Integer,UserMeal> map = repository.get(userMeal.getUserId());
        if(map == null)
        {
            map = new HashMap<>();
        }
        map.put(userMeal.getId(),userMeal);
        repository.put(userMeal.getUserId(),map);
        return userMeal;
    }

    @Override
    public boolean delete(int userid,int id) {
        return repository.get(userid).remove(id) != null;
    }

    @Override
    public UserMeal get(int userid,int id) {
        return repository.get(userid).get(id);
    }

    @Override
    public List<UserMeal> getAll(int userid) {
        return repository.get(userid).values().stream()
                .sorted((m,m2)->m2.getDateTime().compareTo(m.getDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserMeal> getFilteredByDate(int id, LocalDate startDate, LocalDate endDate) {
        return repository.get(id).values().stream()
                .filter(um -> DateUtil.isBetween(um.getDateTime().toLocalDate(), startDate, endDate)).collect(Collectors.toList());
    }
}

