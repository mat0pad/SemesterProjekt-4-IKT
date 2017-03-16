package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.Database.IFirebaseAuth;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public class LoginUserModel implements ILoginUserModel {

    private String email;
    private String password;
    private IFirebaseAuth firebaseAuth;

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
    public boolean checkUserValidity() {
        return true;
    }
}
