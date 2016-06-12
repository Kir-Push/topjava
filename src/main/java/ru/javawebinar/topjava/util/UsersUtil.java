package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.util.Arrays;
import java.util.List;

/**
 * Created by buhalo on 12.06.16.
 */
public class UsersUtil {
    public static final List<User> USER_LIST = Arrays.asList(
            new User(null,"anton","petja@gmail.com","25900913", Role.ROLE_USER),
            new User(null,"admin","admin@gmail.com","admin",Role.ROLE_ADMIN,Role.ROLE_USER)
    );
}
