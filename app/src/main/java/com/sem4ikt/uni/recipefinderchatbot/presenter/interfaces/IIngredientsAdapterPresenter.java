package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.ExtendedIngredientModel;

import java.util.List;

/**
 * Created by mathiaspedersen on 28/03/2017.
 */

public interface IIngredientsAdapterPresenter<V> extends IBasePresenter<V> {

    void addAll(List<ExtendedIngredientModel> list);

}
