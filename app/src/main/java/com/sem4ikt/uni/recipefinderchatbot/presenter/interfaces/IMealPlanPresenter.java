package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import java.util.Date;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public interface IMealPlanPresenter<V> extends IBasePresenter<V> {

    void getMealPlanDay();

    void getMealPlanWeek();

    void doNext();

    void doPrevious();

    Date setDateToTwelve(Date selectedDate);

    void update();

    Date getTime();

    Date decrementDay(Date date, int dayInWeek);

    void doBreakfast();

    void doDinner();

    void doLunch();
}
