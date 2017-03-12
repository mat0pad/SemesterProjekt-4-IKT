package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.view.IChatbotView;

/**
 * Created by mathiaslykkepedersen on 09/03/2017.
 */

public class ChatbotPresenter extends BasePresenter<IChatbotView> implements IChatbotPresenter<IChatbotView>{

    public ChatbotPresenter(IChatbotView view){
        super(view);

    }

    public void send(String input){

        // Do something with watson conversation here!

        view.displayNormalResult(input);
    }
}
