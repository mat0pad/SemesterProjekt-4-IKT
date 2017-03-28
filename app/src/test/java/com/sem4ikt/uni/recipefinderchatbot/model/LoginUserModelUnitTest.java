package com.sem4ikt.uni.recipefinderchatbot.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.runners.MockitoJUnitRunner;
/**
 * Created by mathiaspedersen on 26/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginUserModelUnitTest {

    private LoginUserModel model;

    @Before
    public void setup() throws Exception{

        model = new LoginUserModel();
    }

    @Test
    public void setEmail()
    {
       model.setEmail("test");

        Assert.assertEquals("test", model.getEmail());
    }

    @Test
    public void setPass()
    {
        model.setPassword("test");

        Assert.assertEquals("test", model.getPassword());
    }

    @Test
    public void checkValidFailedBothEmailPassWrong()
    {
        model.setPassword("test");
        model.setEmail("test");

        Assert.assertFalse(model.checkUserValidity());
    }

    @Test
    public void checkValidFailedPassTooShort()
    {
        model.setPassword("test");
        model.setEmail("test@test.com");

        Assert.assertFalse(model.checkUserValidity());
    }

    @Test
    public void checkValidFailedEmailNotValid()
    {
        model.setPassword("test123");
        model.setEmail("testtest.com");

        Assert.assertFalse(model.checkUserValidity());
    }

    @Test
    public void checkValidSuccessEmailAndPassValid()
    {
        model.setPassword("test123");
        model.setEmail("test@test.com");

        Assert.assertTrue(model.checkUserValidity());
    }
}
