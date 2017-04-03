package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IDetailRecipeInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IFirebaseInteractor;
import com.sem4ikt.uni.recipefinderchatbot.view.IDetailRecipeView;

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
public class DetailRecipePresenterUnitTest {

    @Mock
    IDetailRecipeView view;
    IDetailRecipeInteractor interactor;
    IFirebaseInteractor interactorDB;

    private DetailRecipePresenter presenter;


    @Before
    public void setup() throws Exception {

        presenter = new DetailRecipePresenter(view, interactorDB, interactor);
    }

    @Test
    public void clearViewOnDestroy() {
        presenter.clearView();

        Assert.assertEquals(presenter.getView(), null);
    }

    @Test
    public void setView() {
        presenter.clearView();
        presenter.setView(view);

        Assert.assertEquals(presenter.getView(), view);
    }

}