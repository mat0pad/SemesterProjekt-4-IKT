package com.sem4ikt.uni.recipefinderchatbot.database;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.FavoritesPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IFavoritesPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 01-04-2017.
 */

public class RecipeInteractor implements IFirebaseDBInteractors.IRecipeInteractor {

    private DatabaseReference recipeDatabase;
    private IFavoritesPresenter callback;

    public RecipeInteractor(IFavoritesPresenter callback) {
        recipeDatabase = FirebaseDatabase.getInstance().getReference("recipe/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
        this.callback = callback;
    }

    @Override
    public void addRecipe(RecipesModel recipe) {
        recipeDatabase.push().setValue(recipe);
    }

    @Override
    public void removeRecipe(RecipesModel recipe) {
        Query recipeQuery = recipeDatabase.orderByChild("id").equalTo(recipe.getId());

        recipeQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot recipeSnapshot : dataSnapshot.getChildren()){
                    recipeSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled",databaseError.toString());
            }
        });
    }

    @Override
    public void getRecipes() {
        recipeDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<RecipesModel> recipeList = new ArrayList<>();
                for(DataSnapshot recipeSnapshot: dataSnapshot.getChildren()) {
                    recipeList.add(recipeSnapshot.getValue(RecipesModel.class));
                }
                callback.onReceived(recipeList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled",databaseError.getMessage());
            }
        });
    }
}
