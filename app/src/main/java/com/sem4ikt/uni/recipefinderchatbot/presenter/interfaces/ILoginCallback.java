package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter;

public interface ILoginCallback {

    void onAuthenticationFinished(LoginPresenter.AUTH auth, String reason);
}
