package com.sem4ikt.uni.recipefinderchatbot.IntegrationTest.model;

import com.google.firebase.auth.FirebaseAuth;
import com.sem4ikt.uni.recipefinderchatbot.database.Authentication;
import com.sem4ikt.uni.recipefinderchatbot.database.DeleteInfoInteractor;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackRecipe;
import com.sem4ikt.uni.recipefinderchatbot.database.RecipeInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ILoginCallback;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertTrue;

/**
 * Created by henriknielsen on 23/04/2017.
 */




public class RecipeInteractorIntegrationTest {

    private CountDownLatch signal;

    private List<RecipeModel> tmpTestList;

    private RecipeInteractor ri = new RecipeInteractor();

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

        DeleteInfoInteractor info = new DeleteInfoInteractor();
        info.removeAllUserInfo();

        signal.await();
    }

    @Test
    public void addAndGetRecipe() throws InterruptedException {

        signal = new CountDownLatch(1);

        RecipeModel rm = new RecipeModel();
        String testTile = "integrationTest";
        rm.setId(123);

        rm.setTitle(testTile);

        ri.addRecipe(rm);

        ri.getRecipe(new ICallbackRecipe() {
            @Override
            public void onReceived(Object recipe, RECIPE_CALLBACK_TYPE type) {
                tmpTestList = (List<RecipeModel>) recipe;
                signal.countDown();
            }
        });

        boolean testPassed = false;

        signal.await();

        ri.removeRecipe(rm);

        for (RecipeModel model:
             tmpTestList) {
            if(model.getTitle().equals(testTile)) {
                testPassed = true;
                break;
            }
        }

        assertTrue(testPassed);
    }


    @Test
    public void removeRecipe() throws InterruptedException {

        signal = new CountDownLatch(1);

        RecipeModel rm = new RecipeModel();
        String testTile = "integrationTest";

        rm.setTitle(testTile);
        rm.setId(123);

        ri.addRecipe(rm);
        ri.removeRecipe(rm);

        ri.getRecipe(new ICallbackRecipe() {
            @Override
            public void onReceived(Object recipe, RECIPE_CALLBACK_TYPE type) {
                tmpTestList = (List<RecipeModel>) recipe;
                signal.countDown();
            }
        });

        boolean testPassed = false;

        signal.await();

        System.out.println("TESTLIST " + tmpTestList);

        if(tmpTestList.size() == 0)
            testPassed = true;

        assertTrue(testPassed);
    }
}
