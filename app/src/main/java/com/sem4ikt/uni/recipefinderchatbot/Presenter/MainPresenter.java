package com.sem4ikt.uni.recipefinderchatbot.Presenter;

import com.sem4ikt.uni.recipefinderchatbot.View.IMainView;

/**
 * Created by mathiaslykkepedersen on 02/03/2017.
 */

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter<IMainView> {

    public MainPresenter(IMainView mainView){
        super(mainView);
    }

    @Override
    public void displayFragment(int fragment) {
        view.showFragment(fragment);
    }

    @Override
    public void setupMenu() {
        view.setup();
    }


}
