package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB.FirebaseInteractor;
import com.sem4ikt.uni.recipefinderchatbot.database.FirebaseDB.IFirebaseInteractor;
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
    private IFirebaseInteractor DBinteractor;

    public DetailRecipePresenter(IDetailRecipeView view) {
        super(view);

        interactor = new DetailRecipeInteractor();
        DBinteractor = new FirebaseInteractor();
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
        DBinteractor.saveRecipe(recipe);
    }

    @Override
    public void doDeleteRecipe(RecipeModel recipe) {
        DBinteractor.deleteRecipe(recipe);
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

        if (instructions.size() >= 1) {

            int size = instructions.get(0).getSteps().size();

            for (int i = 0; i < size; i++)
                steps += "• " + instructions.get(0).getSteps().get(i).getStep() + "\n" + (i != size - 1 ? "\n" : "");
        }

        view.setInstructions(steps);
    }

    public enum CALL_TYPE {GET_RECIPE, SUMMARIZE, SIMILAR, INSTRUCTION}
}
