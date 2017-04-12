package com.sem4ikt.uni.recipefinderchatbot.view;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

import java.util.List;

/**
 * Created by anton on 06-04-2017.
 */

public interface IFavoritesGridAdapterView {
    void notifyUpdate();

    List<RecipeModel> getList();


    void addRecipe(RecipeModel recipe);

    void setList(List<RecipeModel> list);

    void setFullList(List<RecipeModel> list);

    RecipeModel getItem(int index);

    void showRecipe(RecipeModel recipe);

    void deleteRecipe(RecipeModel recipe);

    void setDeleting(boolean isdeleting);

    void deleteRecipe(int position);
}
