package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.EquipmentModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IEquipmentsGridAdapterView;

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

    @Test
    public void addAll() {

        List<EquipmentModel> list = new ArrayList<>();

        EquipmentModel model = new EquipmentModel();

        list.add(model);

        presenter.addAll(list);

        verify(view, times(1)).addItem(model);
        verify(view, times(1)).notifyUpdate();
    }

}