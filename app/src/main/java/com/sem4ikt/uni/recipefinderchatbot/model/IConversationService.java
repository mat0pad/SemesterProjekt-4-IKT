package com.sem4ikt.uni.recipefinderchatbot.model;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

/**
 * Created by henriknielsen on 14/03/2017.
 */

public interface IConversationService {
    void message(String workspaceId, String msg);
    IConversationService setConversationUsernameAndPassword(String username, String password);
    IConversationService setToneAnalyzerUsernameAndPassword(String username, String password);
}
