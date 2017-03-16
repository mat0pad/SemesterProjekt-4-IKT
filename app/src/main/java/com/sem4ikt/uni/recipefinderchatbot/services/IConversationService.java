package com.sem4ikt.uni.recipefinderchatbot.services;

/**
 * Created by henriknielsen on 14/03/2017.
 */

public interface IConversationService {
    void message(String workspaceId, String msg);
    IConversationService setConversationUsernameAndPassword(String username, String password);
    IConversationService setToneAnalyzerUsernameAndPassword(String username, String password);
}
