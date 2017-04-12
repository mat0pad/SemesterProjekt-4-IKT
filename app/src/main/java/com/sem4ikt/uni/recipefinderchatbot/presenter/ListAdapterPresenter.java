package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.adapter.ListDataContainer;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IListRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IRecipeAdapterListView;

/**
 * Created by henriknielsen on 30/03/2017.
 */

public class ListAdapterPresenter extends BasePresenter<IRecipeAdapterListView> implements IListRecipePresenter {
    public ListAdapterPresenter(IRecipeAdapterListView view) {
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
