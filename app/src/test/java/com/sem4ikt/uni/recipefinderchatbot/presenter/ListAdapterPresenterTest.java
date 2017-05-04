package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.adapter.ListDataContainer;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ListDataModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IRecipeAdapterListView;

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
 * Created by mathiaslykkepedersen on 04/05/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ListAdapterPresenterTest {

    @Mock
    IRecipeAdapterListView view;

    private ListAdapterPresenter presenter;


    @Before
    public void setup() throws Exception {

        presenter = new ListAdapterPresenter(view);
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

        List<Object> list = new ArrayList<>();

        Object temp = new Object();

        list.add(temp);

        ListDataContainer container = new ListDataContainer(list, ListDataModel.ListDataType.RECIPE);

        presenter.setContainer(container);

        verify(view, times(1)).addItem(temp);
        verify(view, times(1)).notifyUpdate();
    }
}
