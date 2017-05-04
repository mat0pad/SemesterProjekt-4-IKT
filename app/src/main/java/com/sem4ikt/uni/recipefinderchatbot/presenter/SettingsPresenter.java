package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseAuth;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ILoginUserModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ILoginCallback;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ISettingsPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ISettingsView;

/**
 * Created by Christian on 07-04-2017.
 */

public class SettingsPresenter extends BasePresenter<ISettingsView> implements ISettingsPresenter<ISettingsView>, ILoginCallback {

    private IFirebaseAuth auth;
    private IFirebaseDBInteractors.IDeleteInfoInteractor dbDelete;
    private ILoginUserModel passChecker;

    public SettingsPresenter(ISettingsView view, IFirebaseAuth auth, ILoginUserModel loginUserModel, IFirebaseDBInteractors.IDeleteInfoInteractor dbDelete) {
        super(view);

        this.dbDelete = dbDelete;
        this.passChecker = loginUserModel;
        this.auth = auth;
    }

    @Override
    public void doCheckPassSucess(String pass1, String pass2)
    {
        if(pass1 == null || pass2 ==null) {
            view.onShowToast("Password required in both fields");
            return;
        }

        passChecker.setPassword(pass1);
        passChecker.setConfirmPassword(pass2);

        if (passChecker.checkPasswordsMatches()) {
            auth.updatePassword(pass1, this);
            view.onSwitchToSettingsView();
        }
        else
            view.onShowToast("Same password required in both fields");

    }

    @Override
    public void doShowPasswordChangeView()
    {
        view.onSwitchToChangePassView();
    }

    @Override
    public void doShowSettingsView()
    {
        view.onSwitchToSettingsView();
    }

    @Override
    public void doDeleteAccount() {
        dbDelete.removeAllUserInfo();
        auth.deleteAccount(this);
    }

    @Override
    public void doConfirmDeleteAccount() {
        view.onShowConfirmDeleteDialog();
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
