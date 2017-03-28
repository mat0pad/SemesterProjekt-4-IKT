package com.sem4ikt.uni.recipefinderchatbot.view;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;

/**
 * Created by mathiaspedersen on 28/03/2017.
 */

public interface ISimilarGridAdapterView {

    void showSimilar(int id);

    void notifyUpdate();

    void addItem(RecipesModel item);

    RecipesModel getItem(int i);
}
