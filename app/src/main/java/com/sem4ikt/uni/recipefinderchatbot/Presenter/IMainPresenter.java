package com.sem4ikt.uni.recipefinderchatbot.Presenter;

/**
 * Created by mathiaslykkepedersen on 02/03/2017.
 */

public interface IMainPresenter<V> extends IBasePresenter<V>{

    void displayFragment(int fragment);
    void setupMenu();

}
