package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public interface IMealPlanPresenter<V> extends IBasePresenter<V> {

    void getMealPlanDay();

    void getMealPlanWeek();

    void doNext();

    void doPrevious();

    void doBreakfast();

    void doDinner();

    void doLunch();
}
