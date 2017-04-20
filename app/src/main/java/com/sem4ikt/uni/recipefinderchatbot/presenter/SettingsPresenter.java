package com.sem4ikt.uni.recipefinderchatbot.presenter;

import android.support.annotation.VisibleForTesting;

import com.sem4ikt.uni.recipefinderchatbot.database.Authentication;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseAuth;
import com.sem4ikt.uni.recipefinderchatbot.model.LoginUserModel;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ILoginUserModel;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ISettingsModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ISettingsPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ISettingsView;

/**
 * Created by Christian on 07-04-2017.
 */

public class SettingsPresenter extends BasePresenter<ISettingsView> implements ISettingsPresenter<ISettingsView>{

    private ISettingsModel settings;
    private IFirebaseAuth auth;

    public SettingsPresenter(ISettingsView view) {
        super(view);

        // Create model
        auth = new Authentication();
    }

    @VisibleForTesting
    public SettingsPresenter(ISettingsView view, IFirebaseAuth auth) {
        super(view);

        this.auth = auth;
    }


    public void doCheckPassSucess(String pass1, String pass2)
    {
        ILoginUserModel passChecker = new LoginUserModel();
        passChecker.setPassword(pass1);
        passChecker.setConfirmPassword(pass2);
        boolean b = passChecker.checkPasswordsMatches();
    }

    public void doShowPasswordChangeView()
    {
        view.switchToChangePassView();
    }

    public void doShowSettingsView()
    {
        view.switchToSettingsView();
    }

    @Override
    public void onAuthenticationFinished(LoginPresenter.AUTH auth, String reason) {

        switch (auth) {

            case UPDATE_PASSWORD_SUCCESS:
                view.onShowToast(reason);
                break;
            case UPDATE_PASSWORD_FAILED:
                view.onShowToast(reason);
                break;
            case DELETE_ACCOUNT_SUCCESS:
                view.onShowToast(reason);
                view.onFinish();
                break;
            case DELETE_ACCOUNT_FAILED:
                view.onShowToast(reason);
                break;

            default:
                break;
        }
    }

}
