package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IDetailRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IDetailRecipeView;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public class DetailRecipePresenter extends BasePresenter<IDetailRecipeView> implements IDetailRecipePresenter<IDetailRecipeView> {

    public DetailRecipePresenter(IDetailRecipeView view) {
        super(view);
    }

}
