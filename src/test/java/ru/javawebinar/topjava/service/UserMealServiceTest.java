package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by buhalo on 23.06.16.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal userMeal = service.get(KEFIR_ID,ADMIN_ID);
        MATCHER.assertEquals(KEFIR,userMeal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetForeignUser() throws Exception {
        service.get(KEFIR_ID,USER_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MOLOKO_ID,USER_ID);
        MATCHER.assertCollectionEquals(Collections.emptyList(),service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteForeignUser() throws Exception {
        service.delete(MOLOKO_ID,ADMIN_ID);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        List<UserMeal> mealWithExceedList = new ArrayList<>(service.getBetweenDates(LocalDate.of(2015,01,01),LocalDate.of(2017,01,01),USER_ID));
        MATCHER.assertCollectionEquals(Collections.singletonList(MOLOKO),mealWithExceedList);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        List<UserMeal> mealWithExceedList = new ArrayList<>(service.getBetweenDateTimes(LocalDateTime.of(2015,01,01,01,01,01),LocalDateTime.of(2016,06,23,01,23),USER_ID));
        MATCHER.assertCollectionEquals(Collections.emptyList(),mealWithExceedList);
    }

    @Test
    public void testGetAll() throws Exception {
        List<UserMeal> mealList = new ArrayList<>(service.getAll(USER_ID));
        mealList.addAll(service.getAll(ADMIN_ID));
        MATCHER.assertCollectionEquals(Arrays.asList(MOLOKO,KEFIR),mealList);
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal userMeal = service.get(100002,100000);
        userMeal.setDescription("молоко2");
        userMeal = service.save(userMeal,USER_ID);
        MATCHER.assertEquals(service.get(userMeal.getId(),100000),userMeal);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateForeignUser() throws Exception {
        UserMeal userMeal = service.get(100002,100001);
        userMeal.setDescription("молоко2");
        userMeal = service.save(userMeal,USER_ID);
    }

    @Test
    public void testUpdateForeignMeal() throws Exception {
        UserMeal userMeal = service.get(100002,USER_ID);
        userMeal.setDescription("молоко2");
        System.out.println(userMeal);
        UserMeal userMeal2 = service.save(userMeal,ADMIN_ID);
        System.out.println(userMeal);
        MATCHER.assertEquals(MOLOKO,service.get(100002,USER_ID));
    }

    @Test
    public void testSave() throws Exception {
        UserMeal userMeal = new UserMeal(LocalDateTime.of(0001,01,01,01,01),"Яблоко",650);
        userMeal = service.save(userMeal,USER_ID);
        List<UserMeal> mealList = new ArrayList<>(service.getAll(USER_ID));
        MATCHER.assertCollectionEquals(Arrays.asList(MOLOKO,userMeal),mealList);
    }

}