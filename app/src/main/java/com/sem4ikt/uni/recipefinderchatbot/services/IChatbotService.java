package com.sem4ikt.uni.recipefinderchatbot.services;

import com.sem4ikt.uni.recipefinderchatbot.model.ChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;

/**
 * Created by henriknielsen on 14/03/2017.
 */

public interface IChatbotService extends ChatbotInteractor.Call {
    void message(String workspaceId, String msg);

    IChatbotService setConversationServiceCredentials(String username, String password);

    IChatbotService setToneAnalyzerCredentials(String username, String password);

    void setChatbotListener(ChatbotInteractor.Callback callback);

    void setUserContextGeneral(User user);
}
