package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anton on 23-04-2017.
 */

public class UserTest {
    public User user;
    @Before
    public void setup()
    {
        user = new User();
    }

    @Test
    public void userNameTest()
    {
        String name = "test";
        user.username = name;
        Assert.assertEquals(name,user.username);
    }

    @Test
    public void userReturningTest()
    {
        boolean isReturning = true;
        user.returninguser = isReturning;
        Assert.assertEquals(isReturning,user.returninguser);
    }
}
