package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.EquipmentModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IEquipmentsAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IEquipmentsGridAdapterView;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public class EquipmentsAdapterPresenter extends BasePresenter<IEquipmentsGridAdapterView> implements IEquipmentsAdapterPresenter<IEquipmentsGridAdapterView> {

    public EquipmentsAdapterPresenter(IEquipmentsGridAdapterView view) {
        super(view);
    }

    @Override
    public void addAll(List<EquipmentModel> list) {
        for (EquipmentModel item : list)
            view.addItem(item);

        view.notifyUpdate();
    }
}
