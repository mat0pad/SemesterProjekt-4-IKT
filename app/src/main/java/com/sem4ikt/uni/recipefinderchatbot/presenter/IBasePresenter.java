package com.sem4ikt.uni.recipefinderchatbot.presenter;

/**
 * Created by mathiaslykkepedersen on 05/03/2017.
 */

interface IBasePresenter<V> {
    void setView(V view);
    void clearView();
}
