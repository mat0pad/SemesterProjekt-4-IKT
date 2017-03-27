package com.sem4ikt.uni.recipefinderchatbot.view;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.InstructionsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.SummaryModel;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public interface IDetailRecipeView {

    void setSummary(SummaryModel model);

    void setSimilar(List<RecipesModel> similar);

    void setRecipe(RecipeModel recipe);

    void setInstructions(List<InstructionsModel> instructions);
}
