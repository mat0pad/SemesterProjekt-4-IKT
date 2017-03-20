package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.database.IFirebaseAuth;
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

    private LoginPresenter presenter;

    @Before
    public void setup() throws Exception{

        presenter = new LoginPresenter(view, model);
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
    public void registerSuccess()
    {
        presenter.onAuthenticationFinished(LoginPresenter.AUTH.CREATE_SUCCESS, "");
        verify(view, times(1)).onRegister(true);
    }

    @Test
    public void  registerFailed()
    {
        presenter.onAuthenticationFinished(LoginPresenter.AUTH.CREATE_FAILED, "");
        verify(view, times(1)).onRegister(false);
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