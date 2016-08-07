package ru.javawebinar.topjava.to;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by buhalo on 01.08.16.
 */
public class UserMealTo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime dateTime;

    @NotEmpty
    private String description;

    @Column(name = "calories", nullable = false)
    @Range(min = 10, max = 5000)
    @NotNull(message = " must not be empty")
    private Integer calories;

    @Override
    public String toString() {
        return "UserMealTo{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public boolean isNew() {
        return id == null;
    }

    public UserMealTo() {

    }

    public UserMealTo(Integer id,LocalDateTime dateTime, String description, int calories) {

        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }
}
