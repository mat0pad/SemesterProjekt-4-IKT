package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.view.ISimilarGridAdapterView;

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
public class SimilarAdapterPresenterUnitTest {

    @Mock
    ISimilarGridAdapterView similarGridAdapterView;

    private SimilarAdapterPresenter presenter;


    @Before
    public void setup() throws Exception {

        presenter = new SimilarAdapterPresenter(similarGridAdapterView);
    }

    @Test
    public void clearViewOnDestroy() {
        presenter.clearView();

        Assert.assertEquals(presenter.getView(), null);
    }

    @Test
    public void setView() {
        presenter.clearView();
        presenter.setView(similarGridAdapterView);

        Assert.assertEquals(presenter.getView(), similarGridAdapterView);
    }

}