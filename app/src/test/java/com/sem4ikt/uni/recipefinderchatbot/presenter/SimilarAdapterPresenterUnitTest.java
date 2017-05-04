package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.view.ISimilarGridAdapterView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class SimilarAdapterPresenterUnitTest {

    @Mock
    ISimilarGridAdapterView view;

    private SimilarAdapterPresenter presenter;


    @Before
    public void setup() throws Exception {

        presenter = new SimilarAdapterPresenter(view);
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

    @Test
    public void addAll() {

        List<RecipesModel> list = new ArrayList<>();

        RecipesModel model = new RecipesModel();

        list.add(model);

        presenter.setList(list);

        verify(view, times(1)).addItem(model);
        verify(view, times(1)).notifyUpdate();
    }

    @Test
    public void showSimilar() {

        presenter.doClick(1);

        verify(view, times(1)).showSimilar(1);
    }

    @Test(expected = NullPointerException.class)
    public void getItemIdException() {

        presenter.getItemId(1);

        verify(view, times(1)).getItem(1).getId();
    }

    @Test
    public void getItemId() {

        int value = presenter.getItemId(1);

        Assert.assertEquals(value, 0);
    }


}