package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import com.sem4ikt.uni.recipefinderchatbot.services.IConversationService;

/**
 * Created by henriknielsen on 14/03/2017.
 */

public class FakeConversationService implements IConversationService {
    @Override
    public void message(String workspaceID, String msg) {

    }

    public FakeConversationService setConversationUsernameAndPassword(String username, String password) {
        return this;
    }

    public FakeConversationService setToneAnalyzerUsernameAndPassword(String username, String password) {
        return this;
    }
}
