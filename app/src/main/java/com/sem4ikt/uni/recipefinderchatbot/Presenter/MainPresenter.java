package com.sem4ikt.uni.recipefinderchatbot.Presenter;

import com.sem4ikt.uni.recipefinderchatbot.View.IMainView;

/**
 * Created by mathiaslykkepedersen on 02/03/2017.
 */

public class MainPresenter implements IMainPresenter {

    private IMainView mainView;

    public MainPresenter(IMainView mainView){

        this.mainView = mainView;
    }

    @Override
    public void displayFragment(int fragment) {
        mainView.showFragment(fragment);
    }

    @Override
    public void setupMenu() {
        mainView.setup();
    }
}
