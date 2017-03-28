package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.model.DetailRecipeInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IDetailRecipeInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.InstructionsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.SummaryModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IDetailRecipeCallback;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IDetailRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IDetailRecipeView;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public class DetailRecipePresenter extends BasePresenter<IDetailRecipeView> implements IDetailRecipePresenter<IDetailRecipeView>, IDetailRecipeCallback {

    private IDetailRecipeInteractor interactor;

    public DetailRecipePresenter(IDetailRecipeView view) {
        super(view);

        interactor = new DetailRecipeInteractor();
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
    public void doSaveRecipe() {

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
                    view.setInstructions((List<InstructionsModel>) model);
                break;

            default:
                break;

        }

    }

    public enum CALL_TYPE {GET_RECIPE, SUMMARIZE, SIMILAR, INSTRUCTION}
}
