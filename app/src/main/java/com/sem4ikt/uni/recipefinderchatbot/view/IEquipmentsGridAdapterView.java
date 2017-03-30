package com.sem4ikt.uni.recipefinderchatbot.view;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.EquipmentModel;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public interface IEquipmentsGridAdapterView {

    void notifyUpdate();

    void addItem(EquipmentModel item);
}
