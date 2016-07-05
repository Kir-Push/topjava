package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by buhalo on 05.07.16.
 */
public class UserRowMapper implements RowMapper<UserMeal> {
    @Override
    public UserMeal mapRow(ResultSet resultSet, int i) throws SQLException {
       UserMeal userMeal = new UserMeal();
        System.out.println("ja TUT");
        userMeal.setDescription(resultSet.getString("description"));
        userMeal.setId(resultSet.getInt("id"));
        userMeal.setCalories(resultSet.getInt("calories"));
        userMeal.setDateTime(resultSet.getTimestamp("date_time").toLocalDateTime());
        return userMeal;
    }
}
