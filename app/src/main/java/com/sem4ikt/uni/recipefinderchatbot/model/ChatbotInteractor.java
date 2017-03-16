package com.sem4ikt.uni.recipefinderchatbot.model;


import android.view.View;

/**
 * Created by henriknielsen on 16/03/2017.
 */

public class ChatbotInteractor implements IChatbotInteractor {

    private ConversationService cs;
    private ChatbotListener listener;

    public interface ChatbotListener {
        // These methods are the different events and
        // need to pass relevant arguments related to the event triggered
        public void onChatbotResponse(String response);
        // or when data has been loaded
        public void onChatbotFailed(String errorMsg);
    }



    public ChatbotInteractor() {
        cs = new ConversationService(listener);
        this.listener = null;
    }

    // Assign the listener implementing events interface that will receive the events
    public void setChatbotListener(ChatbotListener listener) {
        this.listener = listener;
    }


    @Override
    public void message(String msg) {
        cs.message("e665abad-a305-4cf4-a21c-045354782015", msg);
    }
}
