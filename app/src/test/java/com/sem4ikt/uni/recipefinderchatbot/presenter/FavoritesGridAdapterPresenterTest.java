package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.view.IFavoritesGridAdapterView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by mathiaslykkepedersen on 04/05/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class FavoritesGridAdapterPresenterTest {

    @Mock
    IFavoritesGridAdapterView view;

    private FavoritesGridAdapterPresenter presenter;


    @Before
    public void setup() throws Exception {

        presenter = new FavoritesGridAdapterPresenter(view);
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
