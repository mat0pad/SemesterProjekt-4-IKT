package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.ExtendedIngredientModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IIngredientsAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IIngredientsGridAdapterView;

import java.util.List;

/**
 * Created by mathiaspedersen on 28/03/2017.
 */

public class IngredientsAdapterPresenter extends BasePresenter<IIngredientsGridAdapterView> implements IIngredientsAdapterPresenter<IIngredientsGridAdapterView> {

    public IngredientsAdapterPresenter(IIngredientsGridAdapterView view) {
        super(view);
    }

    @Override
    public void addAll(List<ExtendedIngredientModel> list) {
        for (ExtendedIngredientModel item : list)
            view.addItem(item);

        view.notifyUpdate();
    }
}
