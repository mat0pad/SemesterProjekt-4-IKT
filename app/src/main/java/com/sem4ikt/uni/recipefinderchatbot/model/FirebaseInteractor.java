package com.sem4ikt.uni.recipefinderchatbot.model;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBRepository;
import com.sem4ikt.uni.recipefinderchatbot.database.MealplanRepository;
import com.sem4ikt.uni.recipefinderchatbot.database.RecipeRepository;
import com.sem4ikt.uni.recipefinderchatbot.database.UserRepository;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IFirebaseInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.ChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatbotView;

import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * Created by anton on 31-03-2017.
 */

public class FirebaseInteractor implements IFirebaseInteractor {

    private IFirebaseDBRepository.IRecipeRepository recipeRepository;
    private IFirebaseDBRepository.IUserRepository userRepository;
    private IFirebaseDBRepository.IMealplanRepository mealplanRepository;
    private IChatbotPresenter presenter;

    private User user = new User();

    public FirebaseInteractor(IChatbotPresenter presenter) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //delete me when correct solution is found
        String uid = user.getUid();

        recipeRepository = new RecipeRepository(uid);

        userRepository = new UserRepository(uid,this);

        mealplanRepository = new MealplanRepository(uid);

        this.presenter = presenter;
    }

    @Override
    public void addRecipe(RecipeModel recipe) {
        recipeRepository.addRecipe(recipe);
    }

    @Override
    public List<RecipeModel> getRecipes() {
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
    public void getUser() {
        userRepository.getUser();
    }

    @Override
    public void callback(User user) {
        presenter.callFromDatabase(user);
    }

    @Override
    public void Test() {
        userRepository.getUser();
    }


}


