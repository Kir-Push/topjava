package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {

    private static final BeanPropertyRowMapper<UserMeal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(UserMeal.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertMeal;

    @Autowired
    public JdbcUserMealRepositoryImpl(DataSource dataSource) {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("MEALS")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public UserMeal save(UserMeal UserMeal, int userId) {
        System.out.println("save");
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id",UserMeal.getId())
                .addValue("description",UserMeal.getDescription())
                .addValue("dateTime",UserMeal.getDateTime())
                .addValue("calories",UserMeal.getCalories())
                .addValue("user_id",userId);

        if(UserMeal.isNew())
        {
          Number newKey = insertMeal.executeAndReturnKey(map);
            UserMeal.setId(newKey.intValue());
        }
        else
        {
            namedParameterJdbcTemplate.update("UPDATE meals SET description=:description, dateTime=:dateTime, calories=:calories WHERE id=:id and user_id=:user_id",map);
        }
        return UserMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        System.out.println("Delete");
        return jdbcTemplate.update("DELETE FROM MEALS WHERE id=? AND user_id=?",id,userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        System.out.println("get");
        List<UserMeal> mealList = jdbcTemplate.query("SELECT * FROM MEALS WHERE id=? AND user_id=?",ROW_MAPPER,id,userId);
        return DataAccessUtils.singleResult(mealList);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        System.out.println("getAll");
        return  getSorted(jdbcTemplate.query("SELECT * FROM MEALS WHERE user_id=?",ROW_MAPPER,userId));
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        System.out.println("getBerween");
        return getSorted(jdbcTemplate.query("SELECT * FROM MEALS WHERE user_id=? AND datetime <= ? AND datetime >= ?",ROW_MAPPER,userId,endDate,startDate));
    }

    private List<UserMeal> getSorted(List<UserMeal> userMeals)
    {
        userMeals.sort((m,m2)->m2.getDateTime().compareTo(m.getDateTime()));
        return userMeals;
    }

}
