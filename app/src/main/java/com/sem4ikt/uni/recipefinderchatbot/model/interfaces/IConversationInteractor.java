package com.sem4ikt.uni.recipefinderchatbot.model.interfaces;

/**
 * Created by mathiaslykkepedersen on 20/03/2017.
 */

public interface IConversationInteractor {

    void performAction(String action, String input);
    void switchWorkspace(String which, String lastInput);

}
