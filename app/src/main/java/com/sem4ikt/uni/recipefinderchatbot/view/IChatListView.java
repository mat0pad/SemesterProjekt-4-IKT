package com.sem4ikt.uni.recipefinderchatbot.view;

import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface IChatListView {

    void onAddMessage(MessageModel m);

    void onClick(int position);
}
