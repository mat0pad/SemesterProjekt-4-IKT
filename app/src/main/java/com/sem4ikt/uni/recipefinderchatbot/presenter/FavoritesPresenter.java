package com.sem4ikt.uni.recipefinderchatbot.presenter;

import android.support.annotation.VisibleForTesting;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackRecipe;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.database.RecipeInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IFavoritesPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IFavoritesView;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public class FavoritesPresenter extends BasePresenter<IFavoritesView> implements IFavoritesPresenter<IFavoritesView>,ICallbackRecipe {

    private IFirebaseDBInteractors.IRecipeInteractor interactor;

    public FavoritesPresenter(IFavoritesView view, IFirebaseDBInteractors.IRecipeInteractor interactor) {
        super(view);

        this.interactor = interactor;
    }

    @Override
    public void getRecipeList() {
        interactor.getRecipe(this);
    }

    @Override
    public void deleteRecipe(RecipeModel recipe) {
        interactor.removeRecipe(recipe);
    }

    @Override
    public void onReceived(List<RecipeModel> recipe) {
            view.setList(recipe);
    }

    @Override
    public void onFailure() {
        view.showRecipeListFailure();
    }
}
