package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 1500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,7,0), "Обед", 400),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 2100),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,22,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 22,20,0), "Ужин", 11110)
        );

        getFilteredMealsWithExceeded(mealList, LocalTime.of(1, 0), LocalTime.of(22, 0), 2000).forEach(System.out::println);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> userMealWithExceedList;
      HashMap<LocalDate,Integer> caloriesMap = new HashMap<>();
    userMealWithExceedList =  mealList.stream()
            .peek(m -> caloriesMap.put(m.getDateTime().toLocalDate(),caloriesMap.getOrDefault(m.getDateTime().toLocalDate(),0)+m.getCalories()))
               .filter(m -> TimeUtil.isBetween(m.getDateTime().toLocalTime(),startTime,endTime))
            .collect(Collectors.toList())
            .stream()
            .map(m -> new UserMealWithExceed(m.getDateTime(),m.getDescription(),m.getCalories(),caloriesMap.get(m.getDateTime().toLocalDate()) > caloriesPerDay))
               .collect(Collectors.toList());
       // userMeals.sort((o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()));
      /*  for(UserMeal userMeal : userMeals)
        {
            exc = caloriesMap.get(userMeal.getDateTime().toLocalDate());
            exceed = exc > caloriesPerDay;
            userMealWithExceedList.add(new UserMealWithExceed(userMeal.getDateTime(),userMeal.getDescription(),userMeal.getCalories(),exceed));
        } */
        return userMealWithExceedList;
    }
}
