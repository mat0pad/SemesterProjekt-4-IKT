package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseAuth;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ISettingsModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ISettingsPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ISettingsView;
import com.sem4ikt.uni.recipefinderchatbot.database.Authentication;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseAuth;

/**
 * Created by Christian on 07-04-2017.
 */

public class SettingsPresenter extends BasePresenter<ISettingsView> implements ISettingsPresenter<ISettingsView>{

    private ISettingsModel settings;
    private IFirebaseAuth auth;

    SettingsPresenter(ISettingsView view) {
        super(view);


    }
}
