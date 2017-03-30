package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.view.IEquipmentsGridAdapterView;

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
public class EquipmentsAdapterPresenterUnitTest {

    @Mock
    IEquipmentsGridAdapterView view;

    private EquipmentsAdapterPresenter presenter;


    @Before
    public void setup() throws Exception {

        presenter = new EquipmentsAdapterPresenter(view);
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