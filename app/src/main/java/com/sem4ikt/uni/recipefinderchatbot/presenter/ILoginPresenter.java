package com.sem4ikt.uni.recipefinderchatbot.presenter;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface ILoginPresenter<V> extends IBasePresenter<V> {

    void clear();
    void doLogin(String email, String password);
    void signInResult(boolean isSuccessful);
    void createUserResult(boolean isSuccessful);
    void forgotPasswordResult(boolean isSuccessful);

    void setProgressBarVisiblity(boolean visibile);

}
