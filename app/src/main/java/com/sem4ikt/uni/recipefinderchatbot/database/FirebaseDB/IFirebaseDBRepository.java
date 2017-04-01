package com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface IFirebaseDBRepository {
    void saveRecipe(RecipeModel recipe);
    void updateName(String name);
    void saveUser(boolean returningUser);
    User getUser();
    void deleteRecipe(RecipeModel recipe);
    void setUid(String uid);
}
