package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.activity.LoginActivity;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface ILoginPresenter<V> extends IBasePresenter<V> {

    void clear();
    void doLogin(String email, String password) throws InterruptedException;
    void doCreateUser(String email, String password, String confirm_password);
    void doForgotPassword(String email);

    void showLayout(LoginActivity.LoginView v);

    void doBack(LoginActivity.LoginView state);
    void setProgressBarVisiblity(boolean visibile);

    void doToast(String text);

}
