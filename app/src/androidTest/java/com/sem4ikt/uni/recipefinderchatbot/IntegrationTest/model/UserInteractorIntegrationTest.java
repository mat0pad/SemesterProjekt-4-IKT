package com.sem4ikt.uni.recipefinderchatbot.IntegrationTest.model;

import com.sem4ikt.uni.recipefinderchatbot.database.Authentication;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackUser;
import com.sem4ikt.uni.recipefinderchatbot.database.UserInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;
import com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ILoginCallback;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by henriknielsen on 23/04/2017.
 */


public class UserInteractorIntegrationTest {

    private CountDownLatch signal;

    private User testUser;

    private UserInteractor ui = new UserInteractor();

    @Before
    public void setup() throws InterruptedException {
        signal = new CountDownLatch(1);
        Authentication auth = new Authentication();

        auth.signIn("201507091@post.au.dk", "test123", new ILoginCallback() {
            @Override
            public void onAuthenticationFinished(LoginPresenter.AUTH auth, String reason) {
                signal.countDown();
            }
        });

        signal.await();
    }

    @Test
    public void addAndGetUser() throws InterruptedException {

        signal = new CountDownLatch(1);

        String username = "testUser";

        User user = new User(username, false);

        ui.addUser(user);

        ui.getUser(new ICallbackUser() {
            @Override
            public void onReceived(User user, UserCallbackType type) {
                testUser = user;
                signal.countDown();
            }

            @Override
            public void onFailure() {

            }
        });


        signal.await();

        ui.removeUser();

        assertTrue(testUser.username.equals(username));
        assertFalse(testUser.returninguser);
    }

    @Test
    public void removeUser() throws InterruptedException {

        signal = new CountDownLatch(1);

        String username = "testUser";

        User user = new User(username, false);

        ui.addUser(user);
        ui.removeUser();

        ui.getUser(new ICallbackUser() {
            @Override
            public void onReceived(User user, UserCallbackType type) {
                testUser = user;
                signal.countDown();
            }

            @Override
            public void onFailure() {

            }
        });

        signal.await();

        System.out.println("USERNAME_DELETE: " + testUser);

        assertTrue(testUser == null);
        //assertFalse(testUser.returninguser);
    }


    @Test
    public void updateUser() throws InterruptedException {

        signal = new CountDownLatch(1);

        String username = "testUserUpdate";

        User user = new User("", false);

        ui.addUser(user);
        ui.updateUser(username, true);

        ui.getUser(new ICallbackUser() {
            @Override
            public void onReceived(User user, UserCallbackType type) {
                testUser = user;
                signal.countDown();
            }

            @Override
            public void onFailure() {

            }
        });

        signal.await();

        System.out.println("USERNAME_UPDATE: " + testUser.username);

        assertTrue(testUser.username.equals(username));
        assertTrue(testUser.returninguser);
    }
}
