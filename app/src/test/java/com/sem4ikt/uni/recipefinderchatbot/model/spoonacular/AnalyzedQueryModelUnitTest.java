package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public class AnalyzedQueryModelUnitTest {

    private AnalyzedQueryModel model = null;

    @Before
    public void setUp() {
        model = new AnalyzedQueryModel();
    }

    @Test
    public void setModifiers() {
        ModifierModel modifierModel = new ModifierModel();
        modifierModel.setName("test");

        List<ModifierModel> list = new ArrayList<>();
        list.add(modifierModel);

        model.setModifiers(list);
        Assert.assertEquals(model.getModifiers(), list);
    }

    @Test
    public void setCuisines() {
        ModifierModel modifierModel = new ModifierModel();
        modifierModel.setName("test");

        List<ModifierModel> list = new ArrayList<>();
        list.add(modifierModel);

        model.setCuisines(list);
        Assert.assertEquals(model.getCuisines(), list);
    }

    @Test
    public void setDishes() {
        DishModel dishModel = new DishModel();
        dishModel.setName("test");

        List<DishModel> list = new ArrayList<>();
        list.add(dishModel);

        model.setDishes(list);
        Assert.assertEquals(model.getDishes(), list);
    }

    @Test
    public void setIngredients() {
        IngredientModel ingredientModel = new IngredientModel();
        ingredientModel.setName("test");

        List<IngredientModel> list = new ArrayList<>();
        list.add(ingredientModel);

        model.setIngredients(list);
        Assert.assertEquals(model.getIngredients(), list);
    }

}
