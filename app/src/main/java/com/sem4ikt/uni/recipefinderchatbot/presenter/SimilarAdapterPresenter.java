package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ISimilarAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ISimilarGridAdapterView;

import java.util.List;

/**
 * Created by mathiaspedersen on 28/03/2017.
 */

public class SimilarAdapterPresenter extends BasePresenter<ISimilarGridAdapterView> implements ISimilarAdapterPresenter<ISimilarGridAdapterView> {

    public SimilarAdapterPresenter(ISimilarGridAdapterView view) {
        super(view);
    }

    @Override
    public void setList(List<RecipesModel> list) {

        for (RecipesModel item : list)
            view.addItem(item);

        view.notifyUpdate();
    }

    @Override
    public void doClick(int id) {
        view.showSimilar(id);
    }

    @Override
    public int getItemId(int position) {
        return view.getItem(position).getId();
    }


}
