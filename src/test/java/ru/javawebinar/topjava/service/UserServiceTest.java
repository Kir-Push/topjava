package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData.*;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static ru.javawebinar.topjava.UserTestData.*;


@ActiveProfiles({Profiles.ACTIVE_DB,Profiles.DATAJPA})
public class UserServiceTest extends AbstractUserServiceTest {
    @Autowired
    UserMealService mealService;

    @Test
    public void testGetWithMeals() throws Exception
    {
        MealTestData.MATCHER.assertCollectionEquals(mealService.getAll(USER_ID),service.getWithMeals(USER_ID).getMeals());
    }

    @Test
    public void testGetWithMealsNotFoundException() throws Exception
    {
        thrown.expect(NotFoundException.class);
        service.getWithMeals(10003);
    }
}