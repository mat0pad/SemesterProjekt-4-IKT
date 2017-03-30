package com.sem4ikt.uni.recipefinderchatbot.view;

import com.sem4ikt.uni.recipefinderchatbot.activity.DetailRecipeActivity;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.EquipmentModel;
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

    void setInstructions(String instructions);

    void setEquipments(List<List<EquipmentModel>> equipments);

    void showContent(boolean shouldShow);

    void showLoader(boolean shouldShow);

    void setRecipeTypeIcon(DetailRecipeActivity.ICON_TYPE type, boolean isVisible);

    void showRecipeTime(String time, boolean shouldShow);

    void showRecipePriceOrPopular(String text, boolean isPrice, boolean shouldShow);
}
