package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.database.RecipeInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IFavoritesPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IFavoritesView;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public class FavoritesPresenter extends BasePresenter<IFavoritesView> implements IFavoritesPresenter<IFavoritesView> {

    private IFirebaseDBInteractors.IRecipeInteractor interactor;
    FavoritesPresenter(IFavoritesView view) {
        super(view);
        interactor = new RecipeInteractor(this);
    }


    @Override
    public void getRecipeList() {
        interactor.getRecipes();
    }

    @Override
    public void onReceived(List<RecipeModel> list) {
        view.displayList(list);
    }
}
