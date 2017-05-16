package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.activity.LoginActivity;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseAuth;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ILoginUserModel;
import com.sem4ikt.uni.recipefinderchatbot.view.ILoginView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.endsWith;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterUnitTest {

    @Mock
    ILoginView view;

    @Mock
    IFirebaseAuth model;

    @Mock
    ILoginUserModel userModel;

    private LoginPresenter presenter;

    @Before
    public void setup() throws Exception{

        presenter = new LoginPresenter(view, model, userModel);
    }

    @Test
    public void signInSuccess()
    {
        presenter.onAuthenticationFinished(LoginPresenter.AUTH.SIGN_IN_SUCCESS, "");
        verify(view, times(1)).onLogin(true);
    }

    @Test
    public void signInFailed()
    {
        presenter.onAuthenticationFinished(LoginPresenter.AUTH.SIGN_IN_FAILED, "");
        verify(view, times(1)).onLogin(false);
    }

    @Test
    public void signInFailedSignIn()
    {
        when(userModel.checkUserValidity()).thenReturn(false);
        presenter.doLogin("test", "test");
        verify(view,times(1)).onShowToast(anyString());
    }

    @Test
    public void dologinCallAuthSignIn()
    {
        when(userModel.checkUserValidity()).thenReturn(true);
        String email = "test";
        presenter.doLogin(email,email);

        verify(model,times(1)).signIn(email,email,presenter);
    }

    @Test
    public void signInModelSetEmailAndPass()
    {
        presenter.doLogin("","");
        verify(userModel, times(1)).setEmail("");
        verify(userModel, times(1)).setPassword("");
    }

    @Test
    public void signInModelCheckValid()
    {
        presenter.doLogin("test", "test");
        verify(userModel, times(1)).checkUserValidity();
    }

    @Test
    public void registerSuccess()
    {
        presenter.onAuthenticationFinished(LoginPresenter.AUTH.CREATE_SUCCESS, "");
        verify(view, times(1)).onRegister(true);
    }

    @Test
    public void registerFailed()
    {
        presenter.onAuthenticationFinished(LoginPresenter.AUTH.CREATE_FAILED, "");
        verify(view, times(1)).onRegister(false);
    }

    @Test
    public void registerFailedCreateUserWithEmailAndPassword()
    {
        presenter.doCreateUser("test", "test", "test");
        verify(model, times(0)).createUserWithEmailAndPassword("test", "test", presenter);
    }

    @Test
    public void forgotPassSuccess()
    {
        presenter.onAuthenticationFinished(LoginPresenter.AUTH.FORGOT_PASSWORD_SUCCESS, "");
        verify(view, times(1)).onPassForgot(true);
    }

    @Test
    public void forgotPassFailed()
    {
        presenter.onAuthenticationFinished(LoginPresenter.AUTH.FORGOT_PASSWORD_FAILED, "");
        verify(view, times(1)).onPassForgot(false);
    }

    @Test
    public void forgotPassSuccessSendRestEmailVerification()
    {
        presenter.doForgotPassword("test@test.com");
        verify(model, times(1)).sendResetEmailVerification("test@test.com", presenter);
    }

    @Test
    public void setProgressVisible()
    {
        presenter.setProgressBarVisiblity(true);
        verify(view, times(1)).onSetProgressVisibility(true);
    }

    @Test
    public void setProgressInVisible()
    {
        presenter.setProgressBarVisiblity(false);
        verify(view, times(1)).onSetProgressVisibility(false);
    }

    @Test
    public void clearText()
    {
        presenter.clear();
        verify(view, times(1)).onClearText();
    }

    @Test
    public void clearViewOnDestroy()
    {
        presenter.clearView();

        Assert.assertEquals(presenter.getView(), null);
    }

    @Test
    public void setView()
    {
        presenter.clearView();
        presenter.setView(view);

        Assert.assertEquals(presenter.getView(), view);
    }

    @Test
    public void doCreateUserAuthCallCreateUserWithEmailAndPassWord()
    {
        when(userModel.checkUserValidity()).thenReturn(true);
        when(userModel.checkPasswordsMatches()).thenReturn(true);
        String email = "email";
        String password = "test";
        presenter.doCreateUser(email,password,password);

        verify(model,times(1)).createUserWithEmailAndPassword(email,password,presenter);
    }

    @Test
    public void doCreateUserAuthCallSetProgressBarVisibility()
    {
        when(userModel.checkUserValidity()).thenReturn(true);
        when(userModel.checkPasswordsMatches()).thenReturn(false);
        String email = "email";
        String password = "test";
        presenter.doCreateUser(email,password,password);

        verify(view,times(1)).onShowToast(anyString());
    }

    @Test
    public void showLayoutTest()
    {
        presenter.showLayout(LoginActivity.LoginView.LOGIN);

        verify(view,times(1)).onPresentView(LoginActivity.LoginView.LOGIN);
    }

    @Test
    public void doBackTestLogin()
    {
        presenter.doBack(LoginActivity.LoginView.LOGIN);

        verify(view,times(1)).onFinish();
    }

    @Test
    public void doBackTestSIGNUP()
    {
        presenter.doBack(LoginActivity.LoginView.SIGN_UP);

        verify(view,times(1)).onPresentView(LoginActivity.LoginView.LOGIN);
    }

    @Test
    public void doToastTest()
    {
        String text = "text";
        presenter.doToast(text);

        verify(view,times(1)).onShowToast(text);
    }

}