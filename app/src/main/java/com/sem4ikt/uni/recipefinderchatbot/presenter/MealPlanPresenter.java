package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IMealPlanPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IMealPlanView;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public class MealPlanPresenter extends BasePresenter<IMealPlanView> implements IMealPlanPresenter<IMealPlanView> {

    MealPlanPresenter(IMealPlanView view) {
        super(view);
    }



}
