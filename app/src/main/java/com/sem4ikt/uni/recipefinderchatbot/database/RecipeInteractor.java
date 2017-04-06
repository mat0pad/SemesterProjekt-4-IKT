package com.sem4ikt.uni.recipefinderchatbot.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IDetailRecipePresenter;

/**
 * Created by anton on 05-04-2017.
 */

public class RecipeInteractor implements IFirebaseDBInteractors.IRecipeInteractor {

    private IDetailRecipePresenter callback;
    private DatabaseReference Database;

    public RecipeInteractor(IDetailRecipePresenter callback)
    {
        Database = FirebaseDatabase.getInstance().getReference("Test");
        this.callback = callback;
    }
    @Override
    public void addRecipe(RecipeModel recipe) {
        Database.push().setValue(recipe);
    }

    @Override
    public void removeRecipe(RecipeModel recipe) {

    }

    @Override
    public void getRecipe() {

    }
}
