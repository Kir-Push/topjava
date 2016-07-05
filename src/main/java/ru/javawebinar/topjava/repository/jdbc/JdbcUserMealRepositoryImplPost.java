package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.UserMeal;

import javax.sql.DataSource;

/**
 * Created by buhalo on 05.07.16.
 */
@Profile(Profiles.POSTGRES)
@Repository
public class JdbcUserMealRepositoryImplPost extends JdbcUserMealRepositoryAbstract {


    @Autowired
    public JdbcUserMealRepositoryImplPost(DataSource dataSource) {
        this.insertUserMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");
        JdbcUserMealRepositoryAbstract.ROW_MAPPER = BeanPropertyRowMapper.newInstance(UserMeal.class);
    }
}
