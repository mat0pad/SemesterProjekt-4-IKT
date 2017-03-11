package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import com.sem4ikt.uni.recipefinderchatbot.model.RecipeModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by anton on 11-03-2017.
 */

public class MealPlanModelTest {
    private MealPlanModel mealPlanModel = null;

    @Before
    public void setUp(){mealPlanModel = new MealPlanModel();}

    @Test
    public void setRecipeModel()
    {
        List<RecipeModel> list = new ArrayList<>();
        list.add(new RecipeModel());
        list.add(new RecipeModel());

        mealPlanModel.setRecipeModels(list);
        Assert.assertEquals(mealPlanModel.getRecipeModels(),list);
    }

    @Test
    public void setNutrientModel()
    {
        NutrientModel nutrientModel = new NutrientModel();
        mealPlanModel.setNutrients(nutrientModel);

        Assert.assertEquals(mealPlanModel.getNutrients(),nutrientModel);
    }

}
