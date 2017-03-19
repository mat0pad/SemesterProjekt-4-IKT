package com.sem4ikt.uni.recipefinderchatbot.presenter;

import android.support.annotation.VisibleForTesting;

import com.sem4ikt.uni.recipefinderchatbot.database.Authentication;
import com.sem4ikt.uni.recipefinderchatbot.database.IFirebaseAuth;
import com.sem4ikt.uni.recipefinderchatbot.model.ILoginUserModel;
import com.sem4ikt.uni.recipefinderchatbot.model.LoginUserModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ILoginPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ILoginView;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter<ILoginView>, ILoginCallback {

    private ILoginUserModel user;
    private IFirebaseAuth auth;

    public enum AUTH{

        SIGN_IN_SUCCESS,
        SIGN_IN_FAILED,
        CREATE_SUCCESS,
        CREATE_FAILED,
        FORGOT_PASSWORD_SUCCESS,
        FORGOT_PASSWORD_FAILED
    }

    public LoginPresenter(ILoginView view){
        super(view);

        user = new LoginUserModel();

        // Create model
        auth = new Authentication();
    }

    @VisibleForTesting
    LoginPresenter(ILoginView view, IFirebaseAuth auth){
        super(view);

        user = new LoginUserModel();

        // Create model
        this.auth = auth;
    }


    @Override
    public void clear() {
        view.onClearText();
    }

    @Override
    public void doLogin(String email, String password) {

        user.setPassword(password);
        user.setEmail(email);

        setProgressBarVisiblity(true);

        if (user.checkUserValidity())
            auth.signIn(email,password, this);

        else
            System.out.println("Incorrect pass or mail");
    }

    @Override
    public void doCreateUser(String email, String password) {

        user.setPassword(password);
        user.setEmail(email);

        setProgressBarVisiblity(true);

        if (user.checkUserValidity())
            auth.createUserWithEmailAndPassword(email,password, this);

        else
            System.out.println("Incorrect pass or mail");
    }

    @Override
    public void doForgotPassword(String email) {

        setProgressBarVisiblity(true);

        auth.sendRestEmailVerification(email, this);

    }


    @Override
    public void onAuthenticationFinished(AUTH auth, String reason) {

        System.out.println("Authentication result: " + reason);

        switch (auth){

            case SIGN_IN_SUCCESS:
                view.onLogin(true);
                break;
            case SIGN_IN_FAILED:
                view.onLogin(false);
                break;
            case CREATE_SUCCESS:
                view.onRegister(true);
                break;
            case CREATE_FAILED:
                view.onRegister(false);
                break;
            case FORGOT_PASSWORD_SUCCESS:
                view.onPassForgot(true);
                break;
            case FORGOT_PASSWORD_FAILED:
                view.onPassForgot(false);
                break;

            default:
                break;
        }
    }


    @Override
    public void setProgressBarVisiblity(boolean visible) {
        view.onSetProgressVisibility(visible);
    }
}

