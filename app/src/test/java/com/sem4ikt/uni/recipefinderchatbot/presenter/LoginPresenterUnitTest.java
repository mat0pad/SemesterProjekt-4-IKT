package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.database.IFirebaseAuth;
import com.sem4ikt.uni.recipefinderchatbot.model.ILoginUserModel;
import com.sem4ikt.uni.recipefinderchatbot.model.LoginUserModel;
import com.sem4ikt.uni.recipefinderchatbot.view.ILoginView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        presenter.doLogin("test", "test");
        verify(model, times(0)).signIn("test", "test", presenter);
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
        presenter.doCreateUser("test", "test");
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
        verify(model, times(1)).sendRestEmailVerification("test@test.com", presenter);
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



}