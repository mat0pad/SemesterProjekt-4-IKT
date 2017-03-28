package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface IChatListAdapterPresenter<V> extends IBasePresenter<V> {

    void addMessage(String m, int direction);
}
