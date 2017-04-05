package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public interface IFavoritesPresenter<V> extends IBasePresenter<V> {
    void getRecipeList();
    void onReceived(List<RecipesModel> list);
}
