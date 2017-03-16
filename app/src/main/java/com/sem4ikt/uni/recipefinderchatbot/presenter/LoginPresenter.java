package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.model.ILoginUserModel;
import com.sem4ikt.uni.recipefinderchatbot.model.LoginUserModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ILoginPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ILoginView;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter<ILoginView> {

    private ILoginUserModel user;

    public LoginPresenter(ILoginView view){
        super(view);

        user = new LoginUserModel();
    }


    @Override
    public void clear() {
        view.onClearText();
    }

    @Override
    public void doLogin(String email, String password) {

        user.setPassword(password);
        user.setEmail(email);

        if (user.checkUserValidity())
            view.onLogin(email,password);
        else
            System.out.println("Incorrect pass or mail");
    }

    @Override
    public void signInResult(boolean isSuccessful) {

        if (isSuccessful)
            view.showMainActivity();
        // else show error message

    }


    @Override
    public void createUserResult(boolean isSuccessful) {

    }

    @Override
    public void forgotPasswordResult(boolean isSuccessful) {

    }

    @Override
    public void setProgressBarVisiblity(boolean visibile) {

    }
}
