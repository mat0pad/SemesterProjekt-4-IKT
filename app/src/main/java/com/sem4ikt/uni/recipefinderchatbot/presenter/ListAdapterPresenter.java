package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.adapter.ListDataContainer;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ListIngredientAdapter;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ListNutrientAdapter;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ListRecipeAdapter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IListRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IListAdapterView;

import java.util.List;

/**
 * Created by henriknielsen on 30/03/2017.
 */

public class ListAdapterPresenter extends BasePresenter<IListAdapterView> implements IListRecipePresenter {
    public ListAdapterPresenter(IListAdapterView view) {
        super(view);
    }

    @Override
    public void setContainer(ListDataContainer container) {
        for (Object model:
                container.getList()) {
            view.addItem(model);
        }

        view.notifyUpdate();
    }
}
