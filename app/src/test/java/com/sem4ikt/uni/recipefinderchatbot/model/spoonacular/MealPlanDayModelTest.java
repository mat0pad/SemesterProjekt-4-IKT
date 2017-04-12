package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 11-03-2017.
 */

public class MealPlanDayModelTest {
    private MealPlanDayModel mealPlanDayModel = null;

    @Before
    public void setUp() {
        mealPlanDayModel = new MealPlanDayModel();
    }

    @Test
    public void setRecipeModel()
    {
        List<RecipeModel> list = new ArrayList<>();
        list.add(new RecipeModel());
        list.add(new RecipeModel());

        mealPlanDayModel.setRecipeModels(list);
        Assert.assertEquals(mealPlanDayModel.getRecipeModels(), list);
    }

    @Test
    public void setNutrientModel()
    {
        NutrientModel nutrientModel = new NutrientModel();
        mealPlanDayModel.setNutrients(nutrientModel);

        Assert.assertEquals(mealPlanDayModel.getNutrients(), nutrientModel);
    }

}
