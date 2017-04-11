package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

import java.util.List;

/**
 * Created by anton on 06-04-2017.
 */

public interface IFavoritesGridAdapterPresenter<V>  extends IBasePresenter<V>{
    List<RecipeModel> getList();

    void addRecipe(RecipeModel recipe);

    void update(List<RecipeModel> list);

    void doSearch(String query);

    void setList(List<RecipeModel> list);

    void onClick(int recipeid);

    int getItemId(int position);

    void deleteRecipe(RecipeModel recipe);
}
