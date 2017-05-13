package com.sem4ikt.uni.recipefinderchatbot.model.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

import java.util.List;
import java.util.Map;

/**
 * Created by anton on 06-04-2017.
 */

public interface ISearchModel {
    List<RecipeModel> searchRecipe(List<RecipeModel> list, String search);
}
