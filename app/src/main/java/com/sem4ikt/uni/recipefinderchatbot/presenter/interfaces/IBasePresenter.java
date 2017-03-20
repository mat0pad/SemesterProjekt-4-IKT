package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

/**
 * Created by mathiaslykkepedersen on 05/03/2017.
 */

public interface IBasePresenter<V> {
    void setView(V view);
    void clearView();
}
