package com.sem4ikt.uni.recipefinderchatbot;

import com.sem4ikt.uni.recipefinderchatbot.presenter.MainPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IMainView;

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
public class MainPresenterUnitTest {

    @Mock
    IMainView mainView;

    private MainPresenter presenter;


    @Before
    public void setup() throws Exception{

        presenter = new MainPresenter(mainView);
    }


    @Test
    public void Setup_Menu()
    {
        presenter.setupMenu();
        verify(mainView, times(1)).setup();
    }

    @Test
    public void Show_Fragment()
    {
        presenter.displayFragment(2);
        verify(mainView, times(1)).showFragment(2);
    }

    @Test
    public void Clear_View_OnDestroy()
    {
        presenter.clearView();

        Assert.assertEquals(presenter.getView(), null);
    }

    @Test
    public void Set_View()
    {
        presenter.clearView();
        presenter.setView(mainView);

        Assert.assertEquals(presenter.getView(), mainView);
    }

}