package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseAuth;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.LoginUserModel;
import com.sem4ikt.uni.recipefinderchatbot.view.ISettingsView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by mathiaslykkepedersen on 22/04/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SettingsPresenterUnitTest {

    @Mock
    ISettingsView view;
    @Mock
    IFirebaseAuth auth;

    @Mock
    IFirebaseDBInteractors.IDeleteInfoInteractor interactor;

    private LoginUserModel model;
    private SettingsPresenter presenter;

    @Before
    public void setup() throws Exception {

        model = new LoginUserModel();

        presenter = new SettingsPresenter(view, auth, model, interactor);
    }

    @Test
    public void showToastOnAuthenticationDeleteFailed() {
        presenter.onAuthenticationFinished(LoginPresenter.AUTH.DELETE_ACCOUNT_FAILED, "test");

        verify(view, times(1)).onShowToast("test");
    }

    @Test
    public void showToastOnAuthenticationDeleteSuccess() {
        presenter.onAuthenticationFinished(LoginPresenter.AUTH.DELETE_ACCOUNT_SUCCESS, "test");

        verify(view, times(1)).onFinish();
        verify(view, times(1)).onShowToast("test");
    }

    @Test
    public void showToastOnAuthenticationUpdateSuccess() {
        presenter.onAuthenticationFinished(LoginPresenter.AUTH.UPDATE_PASSWORD_SUCCESS, "test");

        verify(view, times(1)).onShowToast("test");
    }

    @Test
    public void showToastOnAuthenticationUpdateFailed() {
        presenter.onAuthenticationFinished(LoginPresenter.AUTH.UPDATE_PASSWORD_FAILED, "test");

        verify(view, times(1)).onShowToast("test");
    }

    @Test
    public void showToastOnAuthenticationUpdateDefault() {
        presenter.onAuthenticationFinished(LoginPresenter.AUTH.CREATE_SUCCESS, "test");

        verify(view, times(0)).onFinish();
    }

    @Test
    public void authDeleteAccountPressed() {
        presenter.doDeleteAccount();

        verify(auth, times(1)).deleteAccount(presenter);
    }

    @Test
    public void interactorDeleteAccountPressed() {
        presenter.doDeleteAccount();

        verify(interactor, times(1)).removeAllUserInfo();
    }

    @Test
    public void confirmDeleteAccount() {
        presenter.doConfirmDeleteAccount();

        verify(view, times(1)).onShowConfirmDeleteDialog();
    }

    @Test
    public void showPasswordChangeView() {
        presenter.doShowPasswordChangeView();

        verify(view, times(1)).onSwitchToChangePassView();
    }

    @Test
    public void showSettingsView() {
        presenter.doShowSettingsView();

        verify(view, times(1)).onSwitchToSettingsView();
    }

    @Test
    public void updatePasswordSuccess() {

        presenter.doCheckPassSucess("test123", "test123");

        verify(auth, times(1)).updatePassword("test123", presenter);
    }


    @Test
    public void checkPassword() {

        presenter.doCheckPassSucess("test123", "test123");

        Assert.assertEquals(model.getPassword(), "test123");
        Assert.assertEquals(model.getConfirmPassword(), "test123");
    }

    @Test
    public void updatePasswordModelCheckSuccess() {

        presenter.doCheckPassSucess("test123", "test123");

        Assert.assertEquals(model.checkPasswordsMatches(), true);

        verify(view, times(1)).onSwitchToSettingsView();
    }

    @Test
    public void updatePasswordModelCheckFailed() {

        presenter.doCheckPassSucess("test123", "test12");

        Assert.assertEquals(model.checkPasswordsMatches(), false);
        verify(view, times(1)).onShowToast("Same password required in both fields");
    }


    @Test
    public void updatePasswordNullNull() {

        presenter.doCheckPassSucess(null, null);

        verify(view, times(1)).onShowToast("Password required in both fields");
    }

    @Test
    public void clearViewOnDestroy() {
        presenter.clearView();

        Assert.assertEquals(presenter.getView(), null);
    }

    @Test
    public void setView() {

        presenter.setView(view);

        Assert.assertEquals(presenter.getView(), view);
    }


}
