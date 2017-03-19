package com.sem4ikt.uni.recipefinderchatbot.presenter;

public interface ILoginCallback {

    void onAuthenticationFinished(LoginPresenter.AUTH auth, String reason);
}
