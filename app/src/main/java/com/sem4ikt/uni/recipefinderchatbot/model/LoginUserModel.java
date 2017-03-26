package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ILoginUserModel;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public class LoginUserModel implements ILoginUserModel {

    private String email;
    private String password;
    private String confirmPassword;

    public LoginUserModel(){

    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setConfirmPassword(String confirmPassword) {this.confirmPassword = confirmPassword;}

    @Override
    public boolean checkUserValidity() {

        //String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w]-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        //String PASS_REGEX = "[a-zA-Z ]*\\d+.*";

        return getPassword().length() >= 6 && !email.isEmpty(); //&& getPassword().matches(PASS_REGEX) && email.matches(EMAIL_REGEX);
    }
}
