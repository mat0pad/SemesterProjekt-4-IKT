package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.presenter.DetailRecipePresenter;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public interface IDetailRecipeCallback {

    void onReceived(Object model, DetailRecipePresenter.CALL_TYPE type);

    enum CALL_TYPE {GET_RECIPE, SUMMARIZE, SIMILAR, INSTRUCTION}
}
