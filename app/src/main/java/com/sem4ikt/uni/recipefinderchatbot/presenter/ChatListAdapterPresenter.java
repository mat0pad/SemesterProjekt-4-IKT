package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatListAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatListAdapterView;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public class  ChatListAdapterPresenter extends BasePresenter<IChatListAdapterView> implements IChatListAdapterPresenter<IChatListAdapterView> {

    public ChatListAdapterPresenter(IChatListAdapterView view) {
        super(view);
    }

    @Override
    public void addMessage(MessageModel m) {
        view.onAddMessage(m);
    }

    @Override
    public void doClick(int position) {
        view.onClick(position);
    }


}
