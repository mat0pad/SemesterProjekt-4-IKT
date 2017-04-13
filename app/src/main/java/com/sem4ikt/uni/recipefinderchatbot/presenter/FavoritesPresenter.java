package com.sem4ikt.uni.recipefinderchatbot.presenter;

import android.util.Log;
import android.widget.Switch;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackRecipe;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.database.RecipeInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IFavoritesPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IFavoritesView;

import java.util.List;
import java.util.Objects;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public class FavoritesPresenter extends BasePresenter<IFavoritesView> implements IFavoritesPresenter<IFavoritesView>,ICallbackRecipe {

    private IFirebaseDBInteractors.IRecipeInteractor interactor;
    public FavoritesPresenter(IFavoritesView view) {
        super(view);
        interactor = new RecipeInteractor();
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
    public void onReceived(Object recipe,RECIPE_CALLBACK_TYPE type) {
        switch (type)
        {
            case DELETE_RECIPE:
                Log.e("PresenterOnReceived","DELETE_RECIPE " + ((RecipeModel) recipe).getId());
                view.deleteRecipe((RecipeModel)recipe);

                break;
            case ADD_RECIPE:
                view.addRecipe((RecipeModel)recipe);
                break;
            case GET_RECIPELIST:
                if(recipe != null)
                    view.setList((List<RecipeModel>)  recipe);
                break;
            default:
                break;
        }
    }

}
