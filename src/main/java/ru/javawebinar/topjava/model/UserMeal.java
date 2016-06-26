package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
@NamedQueries({
        @NamedQuery(name = UserMeal.BY_ID, query = "SELECT m FROM UserMeal m WHERE m.id=:id AND m.user.id=:user_id"),
        @NamedQuery(name = UserMeal.ALL, query = "SELECT m FROM UserMeal m WHERE m.user.id=:user_id ORDER BY m.dateTime"),
        @NamedQuery(name = UserMeal.BETWEEN_DATES, query = "SELECT m FROM UserMeal m  WHERE m.user.id=:user_id AND m.dateTime  BETWEEN :startDate AND :endDate ORDER BY m.dateTime"),
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal m WHERE m.user.id=:user_id AND m.id=:id"),
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"date_time" ,"user_id"}, name = "meals_unique_user_datetime_idx")})
public class UserMeal extends BaseEntity {

    public static final String BY_ID = "UserMeal.BY_ID";
    public static final String ALL = "UserMeal.ALL";
    public static final String BETWEEN_DATES= "UserMeal.BETWEEN_DATES";
    public static final String DELETE = "UserMeal.DELETE";

    @Column(name = "date_time", nullable = false, unique = true)
    @Convert(converter = LocalDateTimeConverter.class)
    protected LocalDateTime dateTime;

    @Column(name = " description", nullable = false)
    @NotEmpty
    protected String description;

    @Column(name = "calories", nullable = false)
    protected int calories;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    protected User user;

    public UserMeal() {
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
