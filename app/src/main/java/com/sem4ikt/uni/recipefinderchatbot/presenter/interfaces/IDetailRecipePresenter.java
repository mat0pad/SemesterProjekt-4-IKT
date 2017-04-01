package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public interface IDetailRecipePresenter<V> extends IBasePresenter<V> {

    void doLoadRecipe(int id);

    void doFindSimilar(int id);

    void doSummarize(int id);

    void doInstructions(int id);

    void doShowContent();

    void doSaveRecipe(RecipeModel recipe);

    void doDeleteRecipe(RecipeModel recipe);
}
