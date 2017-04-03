package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;

import java.util.List;

/**
 * Created by henriknielsen on 30/03/2017.
 */

public interface IListRecipePresenter {
    void setRecipeList(List<RecipesModel> list);
}
