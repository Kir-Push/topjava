package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

    public static final int KEFIR_ID = 100003;
    public static final int MOLOKO_ID = 100002;

    public static final int ID = START_SEQ;

    public static final UserMeal MOLOKO = new UserMeal( MOLOKO_ID,LocalDateTime.of(2016,06,23,01,24,00),"молоко",500);
    public static final UserMeal KEFIR = new UserMeal(KEFIR_ID,LocalDateTime.of(2015,06,23,03,55,00),"кефир",60000);

}
