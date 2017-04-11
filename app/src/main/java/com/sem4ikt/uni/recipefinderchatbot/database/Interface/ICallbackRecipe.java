package com.sem4ikt.uni.recipefinderchatbot.database.Interface;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.FavoritesPresenter;

import java.util.List;

/**
 * Created by anton on 06-04-2017.
 */

public interface ICallbackRecipe {
    enum  RECIPE_CALLBACK_TYPE{DELETE_RECIPE,ADD_RECIPE,GET_RECIPELIST};
    void onReceived(Object recipe, FavoritesPresenter.RECIPE_CALLBACK_TYPE type);
}
