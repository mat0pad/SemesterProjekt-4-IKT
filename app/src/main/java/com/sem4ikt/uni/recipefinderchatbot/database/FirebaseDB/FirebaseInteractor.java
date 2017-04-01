package com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

/**
 * Created by anton on 31-03-2017.
 */

public class FirebaseInteractor implements IFirebaseInteractor {
    private IFirebaseDBRepository firebaserepository;

    public FirebaseInteractor()
    {
        this.firebaserepository = new FirebaseDBRepository();
    }

    @Override
    public void saveRecipe(RecipeModel recipe) {
        firebaserepository.saveRecipe(recipe);
    }

    @Override
    public void setUid(String uid) {
        firebaserepository.setUid(uid);

    }

    @Override
    public void saveUser(boolean returningUser) {
        firebaserepository.saveUser(returningUser);
    }

    @Override
    public User getUser() {
       User user = firebaserepository.getUser();
        return user;
    }

    @Override
    public void deleteRecipe(RecipeModel recipe) {
        firebaserepository.deleteRecipe(recipe);
    }

    @Override
    public void updateName(String name) {
        firebaserepository.updateName(name);
    }
}
