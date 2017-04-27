package com.sem4ikt.uni.recipefinderchatbot.IntegrationTest.model;

import com.sem4ikt.uni.recipefinderchatbot.model.MealPlanInteractor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by henriknielsen on 23/04/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MealPlanInteractorIntegrationTest {


    private MealPlanInteractor mpi = new MealPlanInteractor();

    @Test
    public void dayModelTest() {
        mpi.getmealDayPlan("vegan", 1000, "");
        assertTrue(mpi.getDayModel().getNutrients().getCalories() == 1000);
        assertTrue(mpi.getDayModel().getRecipeModels().get(0).getVegan()); // Is vegan?
    }


    @Test
    public void dayModelTestExcludeIngredient() {
        String contains = "tomato";

        mpi.getmealDayPlan("vegan", 1000, contains);
        assertTrue(mpi.getDayModel().getNutrients().getCalories() == 1000);
        assertTrue(mpi.getDayModel().getRecipeModels().get(0).getVegan()); // Is vegan?
        assertFalse(mpi.getDayModel().getRecipeModels().get(0).getInstructions().contains(contains));
    }


    @Test
    public void weekModelTest() {
        mpi.getmealWeekPlan("vegan", 1000, "");
        //assertTrue(mpi.getWeekModel());
        //assertTrue(mpi.getWeekModel()); // Is vegan?
    }

    @Test
    public void weekModelTestExcludeIngredient() {
        String contains = "tomato";

        mpi.getmealDayPlan("vegan", 1000, contains);
        //assertTrue(mpi.getWeekModel());
        //assertTrue(mpi.getWeekModel()); // Is vegan?
        //assertFalse(mpi.getWeekModel());
    }
}