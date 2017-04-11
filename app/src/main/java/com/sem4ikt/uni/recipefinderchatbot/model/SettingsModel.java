package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ISettingsModel;


/**
 * Created by Christian on 07-04-2017.
 */

public class SettingsModel implements ISettingsModel {

    private String password;
    private String confirmPassword;

    @Override
    public boolean checkPasswordsMatches() {
        return confirmPassword.equals(password);
    }

    @Override
    public boolean CheckPasswordValidity(){

        String PASS_REGEX = "((?=.*\\d)(?=.*[a-z]).{6,20})";

        return password.matches(PASS_REGEX);
    }
}
