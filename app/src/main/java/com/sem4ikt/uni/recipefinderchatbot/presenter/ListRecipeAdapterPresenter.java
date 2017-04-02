package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IListRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IListRecipeAdapterView;

import java.util.List;

/**
 * Created by henriknielsen on 30/03/2017.
 */

public class ListRecipeAdapterPresenter extends BasePresenter<IListRecipeAdapterView> implements IListRecipePresenter {
    public ListRecipeAdapterPresenter(IListRecipeAdapterView view) {
        super(view);
    }

    @Override
    public void setRecipeList(List<RecipesModel> list) {
        for (RecipesModel model:
                list) {
            view.addItem(model);
        }

        view.notifyUpdate();
    }
}
