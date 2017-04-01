package com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB.Interface.IFirebaseDBRepository;
import com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB.Interface.IFirebaseInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

/**
 * Created by anton on 31-03-2017.
 */

public class FirebaseInteractor implements IFirebaseInteractor {
    private IFirebaseDBRepository.IRecipeRepository recipeRepository;
    private IFirebaseDBRepository.IUserRepository userRepository;
    private IFirebaseDBRepository.IMealplanRepository mealplanRepository;

    public FirebaseInteractor() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //delete me when correct solution is found
        String uid = user.getUid();

        recipeRepository = new RecipeRepository(uid);

        userRepository = new UserRepository(uid);

        mealplanRepository = new MealplanRepository(uid);
    }

    @Override
    public void addRecipe(RecipeModel recipe) {
        recipeRepository.addRecipe(recipe);
    }

    @Override
    public RecipeModel getRecipes() {
        return recipeRepository.getRecipes();
    }

    @Override
    public void removeRecipe(RecipeModel recipe) {
        recipeRepository.removeRecipe(recipe);
    }


    @Override
    public void addMealplan(MealPlanModel mealplan) {
        mealplanRepository.addMealPlan(mealplan);
    }

    @Override
    public void removeMealplan() {
        mealplanRepository.removeMealplan();
    }

    @Override
    public MealPlanModel getMealplan() {
        return mealplanRepository.getMealplan();
    }


    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public void removeUser() {
        userRepository.removeUser();
    }

    @Override
    public User getUser() {
        return userRepository.getUser();
    }
}


