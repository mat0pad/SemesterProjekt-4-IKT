package com.sem4ikt.uni.recipefinderchatbot.view;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;

import java.util.List;
import java.util.Objects;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public interface IFavoritesView {

    void setList(List<RecipeModel> list);

    void deleteRecipe(RecipeModel recipe);

    void addRecipe(RecipeModel recipe);

    void showRecipeListFailure();
}
