package com.sem4ikt.uni.recipefinderchatbot.adapter;

import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ListDataModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.IngredientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.NutrientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henriknielsen on 06/04/2017.
 */

public class ListContainerFactory {
    public static ListDataContainer createNutrientsListContainer(List<NutrientsModel> source) {

        List<Object> list = new ArrayList<Object>(source);

        return new ListDataContainer(list, ListDataModel.ListDataType.NUTRIENT);
    }

    public static ListDataContainer createRecipesListContainer(List<RecipesModel> source) {

        List<Object> list = new ArrayList<Object>(source);

        return new ListDataContainer(list, ListDataModel.ListDataType.RECIPE);
    }

    public static ListDataContainer createIngredientsListContainer(List<IngredientsModel> source) {

        List<Object> list = new ArrayList<Object>(source);

        return new ListDataContainer(list, ListDataModel.ListDataType.INGREDIENT);
    }

    public static ListDataContainer createRecipeListContainer(List<RecipeModel> source) {

        List<Object> list = new ArrayList<Object>();

        for (RecipeModel model : source) {
            RecipesModel tmpmodel = new RecipesModel();
            tmpmodel.setId(model.getId());
            tmpmodel.setTitle(model.getTitle());
            tmpmodel.setImage(model.getImage());
            tmpmodel.setReadyInMinutes(model.getReadyInMinutes());
            list.add(tmpmodel);
        }

        return new ListDataContainer(list, ListDataModel.ListDataType.RECIPE);
    }
}
