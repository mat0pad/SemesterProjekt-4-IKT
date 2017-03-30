package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatListAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ChatListView;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public class ChatListAdapterPresenter extends BasePresenter<ChatListView> implements IChatListAdapterPresenter<ChatListView> {

    public ChatListAdapterPresenter(ChatListView view) {
        super(view);
    }

    @Override
    public void addMessage(MessageModel m) {
        view.onAddMessage(m);
    }
}
