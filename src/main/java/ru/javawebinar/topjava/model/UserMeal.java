package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal extends BaseEntity {

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private Integer userId;

    public UserMeal(Integer userId, LocalDateTime dateTime, String description, int calories) {
        this(null,userId, dateTime, description, calories);
    }

    public UserMeal(Integer id,Integer userId, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
        this.userId = userId;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }



    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {

        return userId;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
