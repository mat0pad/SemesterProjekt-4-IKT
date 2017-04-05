package com.sem4ikt.uni.recipefinderchatbot.view;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public interface IFavoritesView {
    void displayList(List<RecipesModel> list);

    void Update(String query);
}
