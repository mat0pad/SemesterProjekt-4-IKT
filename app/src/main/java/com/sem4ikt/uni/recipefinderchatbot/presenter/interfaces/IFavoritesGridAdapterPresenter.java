package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

import java.util.List;

/**
 * Created by anton on 06-04-2017.
 */

public interface IFavoritesGridAdapterPresenter<V>  extends IBasePresenter<V>{
    List<RecipeModel> getList();

    void addRecipe(RecipeModel recipe);

    void doSearch(String query);

    void setList(List<RecipeModel> list);

    void onClick(int recipeid);

    RecipeModel getItem(int position);

    void deleteRecipe(RecipeModel recipe);

    void isDeleting(boolean isdeleting);

    void deleteRecipe(int position);
}
