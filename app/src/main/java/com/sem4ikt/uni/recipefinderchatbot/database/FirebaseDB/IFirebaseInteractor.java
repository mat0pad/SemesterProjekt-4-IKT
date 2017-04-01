package com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

/**
 * Created by anton on 31-03-2017.
 */

public interface IFirebaseInteractor {
    void saveRecipe(RecipeModel recipe);
    void setUid(String uid);
    void saveUser(boolean returningUser);
    User getUser();
    void deleteRecipe(RecipeModel recipe);
    void updateName(String name);
}
