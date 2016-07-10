package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserRepositoryImpl implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("USERS")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public User save(User user) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(def);
        try {
            MapSqlParameterSource map = new MapSqlParameterSource()
                    .addValue("id", user.getId())
                    .addValue("name", user.getName())
                    .addValue("email", user.getEmail())
                    .addValue("password", user.getPassword())
                    .addValue("registered", user.getRegistered())
                    .addValue("enabled", user.isEnabled())
                    .addValue("caloriesPerDay", user.getCaloriesPerDay());

            if (user.isNew()) {
                Number newKey = insertUser.executeAndReturnKey(map);
                user.setId(newKey.intValue());
            } else {
                namedParameterJdbcTemplate.update(
                        "UPDATE users SET name=:name, email=:email, password=:password, " +
                                "registered=:registered, enabled=:enabled, calories_per_day=:caloriesPerDay WHERE id=:id", map);
            }
            transactionManager.commit(transactionStatus);
        }
        catch (DataAccessException e)
        {
            transactionManager.rollback(transactionStatus);
           throw e;
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
    }

    @Override
    public List<User> getAll() {
        List<User> users = jdbcTemplate.query("SELECT * FROM users ORDER BY name, email", ROW_MAPPER);

        class UserRole {
            private final Role role;
            private final int userId;

            UserRole(Role role, int userId)
            {
                this.role = role;
                this.userId = userId;
            }

            public Role getRole() {
                return role;
            }

            public int getUserId() {
                return userId;
            }


        }

        List<UserRole> roles = jdbcTemplate.query("SELECT role, user_id FROM user_roles",((rs, rowNum) -> new UserRole(Role.valueOf(rs.getString("role")),rs.getInt("user_id"))));
        users.stream().forEach(user -> user.setRoles(roles.stream().filter(userRole -> userRole.getUserId() == user.getId())
                .collect(
                        Collectors.mapping(UserRole::getRole,Collectors.toSet()))));
        return users;
    }
}
