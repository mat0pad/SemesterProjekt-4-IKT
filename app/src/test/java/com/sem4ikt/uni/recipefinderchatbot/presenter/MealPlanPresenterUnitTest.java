package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.view.IMealPlanView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MealPlanPresenterUnitTest {

    @Mock
    IMealPlanView mealPlanView;

    @Mock
    IFirebaseDBInteractors.IMealplanInteractor interactor;

    private MealPlanPresenter presenter;


    @Before
    public void setup() throws Exception {

        presenter = new MealPlanPresenter(mealPlanView, interactor);
    }

    @Test
    public void clearViewOnDestroy() {

        presenter.clearView();

        Assert.assertEquals(presenter.getView(), null);
    }

    @Test
    public void setView() {
        presenter.clearView();
        presenter.setView(mealPlanView);

        Assert.assertEquals(presenter.getView(), mealPlanView);
    }

}