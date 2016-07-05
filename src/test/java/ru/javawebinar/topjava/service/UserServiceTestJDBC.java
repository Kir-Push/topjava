package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.util.exception.NotFoundException;

/**
 * Created by buhalo on 03.07.16.
 */
@ActiveProfiles({Profiles.ACTIVE_DB,Profiles.JDBC})
public class UserServiceTestJDBC extends AbstractUserServiceTest {

}
