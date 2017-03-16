package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatListPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ChatListView;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public class ChatListPresenter extends BasePresenter<ChatListView> implements IChatListPresenter<ChatListView> {

    public ChatListPresenter(ChatListView view) {
        super(view);
    }

    @Override
    public void addMessage(String m, int direction) {
        view.onAddMessage(m,direction);
    }
}
