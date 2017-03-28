package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public interface IDetailRecipePresenter<V> extends IBasePresenter<V> {

    void doLoadRecipe(int id);

    void doFindSimilar(int id);

    void doSummarize(int id);

    void doInstructions(int id);

    void doSaveRecipe();
}
