package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by buhalo on 03.07.16.
 */
@ActiveProfiles({Profiles.ACTIVE_DB,Profiles.JDBC})
public class UserMealServiceTestJDBC extends AbstractUserMealServiceTest {
}
