package com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB.Interface.IFirebaseDBRepository;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

/**
 * Created by anton on 01-04-2017.
 */

public class RecipeRepository implements IFirebaseDBRepository.IRecipeRepository {
    private DatabaseReference recipeDatabase;
    public RecipeRepository(String uid) {

        recipeDatabase = FirebaseDatabase.getInstance().getReference("recipe/"+uid);
    }

    @Override
    public void addRecipe(RecipeModel recipe) {
        recipeDatabase.push().setValue(recipe);
    }

    @Override
    public void removeRecipe(RecipeModel recipe) {
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
    public RecipeModel getRecipes() {
        return null;
    }
}
