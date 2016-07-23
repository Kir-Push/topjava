package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * Created by buhalo on 21.07.16.
 */
public class UserMealRestControllerTest extends AbstractControllerTest {

    @Autowired
    private UserMealService service;

    private static final String REST_URL = UserMealRestController.REST_URL + '/';

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(UserMealsUtil.getWithExceeded(USER_MEALS,USER.getCaloriesPerDay())));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals(Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2),service.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(put(REST_URL+MEAL1_ID).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.writeValue(getUpdated())))
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals(getUpdated(), service.get(MEAL1_ID, START_SEQ));
    }

    @Test
    public void testCreateWithLocation() throws Exception {
        ResultActions action = mockMvc.perform(post(REST_URL).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.writeValue(getCreated())))
                .andDo(print())
                .andExpect(status().isCreated());
        System.out.println(JsonUtil.writeValue(getCreated()));
        UserMeal returned = MATCHER.fromJsonAction(action);
        UserMeal expected = getCreated();
        expected.setId(returned.getId());
        assertEquals(Arrays.asList(expected,MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1),service.getAll(USER_ID));
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(REST_URL+"between?startDate=2015-05-30T07:00&endDate=2015-05-31T11:00:00").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(UserMealsUtil.createWithExceed(MEAL4,true), UserMealsUtil.createWithExceed(MEAL1,false)));

    }

}