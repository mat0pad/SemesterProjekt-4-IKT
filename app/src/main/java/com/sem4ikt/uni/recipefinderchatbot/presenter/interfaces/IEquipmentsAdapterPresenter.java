package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.EquipmentModel;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public interface IEquipmentsAdapterPresenter<V> extends IBasePresenter<V> {

    void addAll(List<EquipmentModel> list);
}
