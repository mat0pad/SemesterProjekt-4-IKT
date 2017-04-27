package com.sem4ikt.uni.recipefinderchatbot.view;


/**
 * Created by Christian on 07-04-2017.
 */

public interface ISettingsView {

    void onFinish();

    void onShowToast(String text);

    void onSwitchToSettingsView();

    void onSwitchToChangePassView();

    void onShowConfirmDeleteDialog();
}
