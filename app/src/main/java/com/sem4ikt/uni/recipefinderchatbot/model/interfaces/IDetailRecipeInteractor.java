package com.sem4ikt.uni.recipefinderchatbot.model.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IDetailRecipeCallback;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public interface IDetailRecipeInteractor {

    void getRecipe(int id, final IDetailRecipeCallback callback);

    void getSummary(int id, final IDetailRecipeCallback callback);

    void getSimilar(int id, final IDetailRecipeCallback callback);

    void getInstructions(int id, final IDetailRecipeCallback callback);
}
