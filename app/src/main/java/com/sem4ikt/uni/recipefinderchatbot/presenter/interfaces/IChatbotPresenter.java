package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

/**
 * Created by mathiaslykkepedersen on 09/03/2017.
 */

public interface IChatbotPresenter<V> extends IBasePresenter<V> {

    void send(String input);
}

