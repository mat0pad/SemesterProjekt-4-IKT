package com.sem4ikt.uni.recipefinderchatbot.view;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;

/**
 * Created by henriknielsen on 30/03/2017.
 */

public interface IListRecipeAdapterView {
    void addItem(RecipesModel model);
    void update();
}
