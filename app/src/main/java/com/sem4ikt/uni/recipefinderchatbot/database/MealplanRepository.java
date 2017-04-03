package com.sem4ikt.uni.recipefinderchatbot.database;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBRepository;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanModel;

/**
 * Created by anton on 01-04-2017.
 */

public class MealplanRepository implements IFirebaseDBRepository.IMealplanRepository {

    public MealplanRepository(String uid) {

    }

    @Override
    public void addMealPlan(MealPlanModel mealplan) {

    }

    @Override
    public void removeMealplan() {

    }

    @Override
    public MealPlanModel getMealplan() {
        return null;
    }
}
