package com.sem4ikt.uni.recipefinderchatbot.view;

import com.sem4ikt.uni.recipefinderchatbot.activity.LoginActivity;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface ILoginView {

    void onLogin(boolean isSuccessful);
    void onRegister(boolean isSuccessful);
    void onPassForgot(boolean isSuccessful);
    void onClearText();
    void onSetProgressVisibility(boolean visible );

    void onPresentView(LoginActivity.LoginView v);

    void onFinish();

    void onShowToast(String text);
}
