package com.sem4ikt.uni.recipefinderchatbot.view;

import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;

/**
 * Created by mathiaslykkepedersen on 09/03/2017.
 */

public interface IChatbotView {

    void displayNormalMessage(MessageModel m);

    void initText2Speech();

    void play(String text2play);

    void shouldSendButton(boolean shouldDisable);

}
