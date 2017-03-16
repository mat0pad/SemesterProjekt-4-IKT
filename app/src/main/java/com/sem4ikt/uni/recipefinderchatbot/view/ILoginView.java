package com.sem4ikt.uni.recipefinderchatbot.view;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface ILoginView {

    void onLogin(String email, String password);
    void showMainActivity();
    void onClearText();
    void onSetProgressVisibility();
}
