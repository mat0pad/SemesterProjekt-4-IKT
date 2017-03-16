package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.model.ChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.ConversationService;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatbotView;

/**
 * Created by mathiaslykkepedersen on 09/03/2017.
 */

public class ChatbotPresenter extends BasePresenter<IChatbotView> implements IChatbotPresenter<IChatbotView>{

    private ConversationService cs;

    public ChatbotPresenter(IChatbotView view){
        super(view);
        cs = new ConversationService();
        cs.setConversationUsernameAndPassword("f6c68c53-70a5-4a8c-af70-41a5eed85690", "1pMBh1PJOxP0").setToneAnalyzerUsernameAndPassword("48091cfc-fd99-456a-b67c-00bdeef74b06","XQE4Xl4oZuk0");
    }

    public void send(String input){
        // TODO: Change messageTest to message after done testing
        cs.message("e665abad-a305-4cf4-a21c-045354782015", input);
        view.displayNormalResult(input);
    }
}
