package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;

import java.util.List;

/**
 * Created by mathiaspedersen on 28/03/2017.
 */

public interface ISimilarAdapterPresenter<V> extends IBasePresenter<V> {

    void setList(List<RecipesModel> list);

    void doClick(int id);

    int getItemId(int position);

}
