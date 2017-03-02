package com.sem4ikt.uni.recipefinderchatbot;

import com.sem4ikt.uni.recipefinderchatbot.Presenter.MainPresenter;
import com.sem4ikt.uni.recipefinderchatbot.View.IMainView;

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



}