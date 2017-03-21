package com.sem4ikt.uni.recipefinderchatbot.services;

import com.sem4ikt.uni.recipefinderchatbot.model.ChatbotInteractor;

/**
 * Created by henriknielsen on 14/03/2017.
 */

public interface IConversationService extends ChatbotInteractor.Call {
    void message(String workspaceId, String msg);
    IConversationService setConversationServiceCredentials(String username, String password);
    IConversationService setToneAnalyzerCredentials(String username, String password);
    void setChatbotListener(ChatbotInteractor.Callback callback);
}
