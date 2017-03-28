package com.sem4ikt.uni.recipefinderchatbot.view;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.ExtendedIngredientModel;

/**
 * Created by mathiaspedersen on 28/03/2017.
 */

public interface IIngredientsGridAdapterView {

    void notifyUpdate();

    void addItem(ExtendedIngredientModel item);

}
