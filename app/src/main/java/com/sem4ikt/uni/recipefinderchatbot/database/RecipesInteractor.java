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
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IFavoritesPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 01-04-2017.
 */

public class RecipesInteractor implements IFirebaseDBInteractors.IRecipesInteractor {

    private DatabaseReference Database;
    private IFavoritesPresenter callback;

    public RecipesInteractor(IFavoritesPresenter callback) {
        Database = FirebaseDatabase.getInstance().getReference("recipe/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
        this.callback = callback;
    }

    @Override
    public void addRecipe(RecipesModel recipe) {
        Database.push().setValue(recipe);
    }

    @Override
    public void removeRecipe(RecipesModel recipe) {
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
    public void getRecipes() {
        Database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<RecipesModel> recipeList = new ArrayList<>();
                for(DataSnapshot recipesSnapshot: dataSnapshot.getChildren()) {
                    recipeList.add(recipesSnapshot.getValue(RecipesModel.class));
                }
                callback.onReceived(recipeList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled",databaseError.getMessage());
            }
        });
    }

    @Override
    public void searchRecipesbyTitle(final String Query) {
        Database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<RecipesModel> list = new ArrayList<RecipesModel>();
                for(DataSnapshot recipessnapshot : dataSnapshot.getChildren()){
                    RecipesModel recipe = recipessnapshot.getValue(RecipesModel.class);
                    if(recipe.getTitle().contains(Query))
                        list.add(recipe);
                }
                callback.onReceived(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled",databaseError.getMessage());
            }
        });
    }


}
