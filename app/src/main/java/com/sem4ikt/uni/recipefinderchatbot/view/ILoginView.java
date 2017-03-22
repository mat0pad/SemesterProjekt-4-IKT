package com.sem4ikt.uni.recipefinderchatbot.view;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface ILoginView {

    void onLogin(boolean isSuccessful);
    void onRegister(boolean isSuccessful);
    void onAccCreated(boolean isSuccesful);
    void onPassForgot(boolean isSuccessful);
    void onPassSend(boolean isSuccessful);
    void onClearText();
    void onSetProgressVisibility(boolean visible );
}
