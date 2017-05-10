package com.sem4ikt.uni.recipefinderchatbot.database;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackRecipe;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

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
            Database = FirebaseDatabase.getInstance().getReference("Test"); //Cant save data if not logged in

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
                    System.out.println("RECIPEFOUND  "+recipeSnapshot.getValue(RecipeModel.class).getId());
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
                List<RecipeModel> recipeList = new ArrayList<>();
                for(DataSnapshot recipesSnapshot: dataSnapshot.getChildren()) {
                    recipeList.add(recipesSnapshot.getValue(RecipeModel.class));
                }
                callback.onReceived(recipeList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onFailure();
            }
        });
    }



}
