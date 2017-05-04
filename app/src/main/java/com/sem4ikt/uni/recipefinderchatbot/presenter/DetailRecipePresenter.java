package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.activity.DetailRecipeActivity;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IDetailRecipeInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.EquipmentModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.InstructionsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.SummaryModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IDetailRecipeCallback;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IDetailRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IDetailRecipeView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public class DetailRecipePresenter extends BasePresenter<IDetailRecipeView> implements IDetailRecipePresenter<IDetailRecipeView>, IDetailRecipeCallback {

    private IDetailRecipeInteractor interactor;
    private IFirebaseDBInteractors.IRecipeInteractor interactorDB;


    public DetailRecipePresenter(IDetailRecipeView view, IFirebaseDBInteractors.IRecipeInteractor model1, IDetailRecipeInteractor model2) {
        super(view);

        interactor = model2;
        interactorDB = model1;
    }

    @Override
    public void doLoadRecipe(int id) {
        interactor.getRecipe(id, this);
    }

    @Override
    public void doFindSimilar(int id) {
        interactor.getSimilar(id, this);
    }

    @Override
    public void doSummarize(int id) {
        interactor.getSummary(id, this);
    }

    @Override
    public void doInstructions(int id) {
        interactor.getInstructions(id, this);
    }

    @Override
    public void doShowContent() {
        view.showLoader(false);
        view.showContent(true);
    }

    @Override
    public void doSaveRecipe(RecipeModel recipe) {
        interactorDB.addRecipe(recipe);
    }

    @Override
    public void doDeleteRecipe(RecipeModel recipe) {
        interactorDB.removeRecipe(recipe);
    }

    @Override
    public void doSetRecipeIconType(RecipeModel recipe) {

        if (recipe.getVeryPopular()) {
            view.showRecipePriceOrPopular("Very popular!", false, true);
        } else {
            if (recipe.getPricePerServing() != 0) {

                DecimalFormat numberFormat = new DecimalFormat("#.00");
                String inDollars = numberFormat.format(recipe.getPricePerServing() / 100);

                view.showRecipePriceOrPopular("$" + inDollars + " per serving", true, true);
            }
        }

        if (recipe.getReadyInMinutes() != 0) {
            view.showRecipeTime("Ready in " + recipe.getReadyInMinutes() + " minutes", true);
        }

        view.setRecipeTypeIcon(getSpecial(recipe), true);
    }

    @Override
    public void onReceived(Object model, CALL_TYPE type) {

        switch (type) {

            case GET_RECIPE:
                if (model != null)
                    view.setRecipe((RecipeModel) model);
                break;

            case SUMMARIZE:
                if (model != null)
                    view.setSummary((SummaryModel) model);
                break;

            case SIMILAR:
                if (model != null)
                    view.setSimilar((List<RecipesModel>) model);
                break;

            case INSTRUCTION:
                if (model != null)
                    parseInstructions((List<InstructionsModel>) model);
                break;

            default:
                break;

        }

    }

    private void parseInstructions(List<InstructionsModel> instructions) {

        String steps = "";

        List<List<EquipmentModel>> equipments = new ArrayList<>();

        for (int i = 0; i < instructions.size(); i++) {

            equipments.add(instructions.get(0).getSteps().get(i).getEquipment());

            for (int j = 0; j < instructions.get(i).getSteps().size(); j++)
                steps += "• " + instructions.get(i).getSteps().get(j).getStep() + "\n" + (j != instructions.get(i).getSteps().size() - 1 ? "\n" : "");

        }

        view.setEquipments(equipments);
        view.setInstructions(steps);
    }

    private DetailRecipeActivity.ICON_TYPE getSpecial(RecipeModel recipe) {

        DetailRecipeActivity.ICON_TYPE type = DetailRecipeActivity.ICON_TYPE.NONE;

        if (recipe.getVegetarian()) {
            System.out.println("Vegetarian");
            type = DetailRecipeActivity.ICON_TYPE.VEGETARIAN;
        } else if (recipe.getVegan()) {
            System.out.println("Vegan");
            type = DetailRecipeActivity.ICON_TYPE.VEGAN;
        } else if (recipe.getWhole30()) {
            System.out.println("Whole30");
            type = DetailRecipeActivity.ICON_TYPE.WHOLE30;
        } else if (recipe.getLowFodmap()) {
            System.out.println("paleo");
            type = DetailRecipeActivity.ICON_TYPE.PALEO;
        } else if (recipe.getSustainable()) {
            System.out.println("organic");
            type = DetailRecipeActivity.ICON_TYPE.ORGANIC;
        } else if (recipe.getGlutenFree()) {
            System.out.println("GlutenFree");
            type = DetailRecipeActivity.ICON_TYPE.GLUTEN_FREE;
        } else if (recipe.getKetogenic()) {
            System.out.println("Ketogenic");
            type = DetailRecipeActivity.ICON_TYPE.KETOGENIC;
        } else if (recipe.getVeryHealthy()) {
            System.out.println("VeryHealthy");
            type = DetailRecipeActivity.ICON_TYPE.WEIGHT_WATCH;
        }

        return type;
    }



    public enum CALL_TYPE {GET_RECIPE, SUMMARIZE, SIMILAR, INSTRUCTION}
}
