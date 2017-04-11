package com.sem4ikt.uni.recipefinderchatbot.database;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackRecipe;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.FavoritesPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IBasePresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IDetailRecipePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 05-04-2017.
 */

public class RecipeInteractor implements IFirebaseDBInteractors.IRecipeInteractor {

    private DatabaseReference Database;

    public RecipeInteractor()
    {
        if(FirebaseAuth.getInstance().getCurrentUser() != null)
            Database = FirebaseDatabase.getInstance().getReference("Recipe/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
        else
            Database = FirebaseDatabase.getInstance().getReference("Test");
    }
    @Override
    public void addRecipe(RecipeModel recipe) {
        Database.push().setValue(recipe);
    }

    @Override
    public void removeRecipe(RecipeModel recipe) {
        Query recipeQuery = Database.orderByChild("id").equalTo(recipe.getId());

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
    public void getRecipe(final ICallbackRecipe callback) {
        Database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("GetRecipe","call");
                List<RecipeModel> recipeList = new ArrayList<>();
                for(DataSnapshot recipesSnapshot: dataSnapshot.getChildren()) {
                    recipeList.add(recipesSnapshot.getValue(RecipeModel.class));
                }
                callback.onReceived(recipeList, ICallbackRecipe.RECIPE_CALLBACK_TYPE.GET_RECIPELIST);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled",databaseError.getMessage());
            }
        });
    }

    @Override
    public void checkUpdates(final ICallbackRecipe callback) {
         Database.limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e("childadded","childadded");
                RecipeModel recipe = dataSnapshot.getValue(RecipeModel.class);
                callback.onReceived(recipe,ICallbackRecipe.RECIPE_CALLBACK_TYPE.ADD_RECIPE);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.e("childChanged","childChanged");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.e("childremoved","childremoved");
                RecipeModel deleterecipe = dataSnapshot.getValue(RecipeModel.class);
                callback.onReceived(deleterecipe,ICallbackRecipe.RECIPE_CALLBACK_TYPE.DELETE_RECIPE);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled",databaseError.getMessage());
            }
        });
    }
}
