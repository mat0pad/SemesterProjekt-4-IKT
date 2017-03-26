package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface ILoginPresenter<V> extends IBasePresenter<V> {

    void clear();
    void doLogin(String email, String password);
    void doCreateUser(String email, String password, String confirm_password);
    void doForgotPassword(String email);

    void setProgressBarVisiblity(boolean visibile);

}
