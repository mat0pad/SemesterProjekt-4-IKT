package com.sem4ikt.uni.recipefinderchatbot.model.interfaces;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

/**
 * Created by mathiaslykkepedersen on 20/03/2017.
 */

public interface IConversationInteractor {

    void performAction(String action, MessageResponse response);
    void switchWorkspace(String which, String lastInput);

}
