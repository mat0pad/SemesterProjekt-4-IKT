package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface IChatListAdapterPresenter<V> extends IBasePresenter<V> {

    void addMessage(MessageModel m);

    void doClick(int position);
}
