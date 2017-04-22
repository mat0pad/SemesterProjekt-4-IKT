package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter;

/**
 * Created by Christian on 07-04-2017.
 */

public interface ISettingsPresenter<V> extends IBasePresenter<V>{

    void onAuthenticationFinished(LoginPresenter.AUTH auth, String reason);

    void doShowPasswordChangeView();

    void doShowSettingsView();

    void doDeleteAccount();

    void doCheckPassSucess(String pass1, String pass2);
}
