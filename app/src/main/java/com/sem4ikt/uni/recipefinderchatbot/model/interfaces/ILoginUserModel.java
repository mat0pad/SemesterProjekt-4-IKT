package com.sem4ikt.uni.recipefinderchatbot.model.interfaces;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface ILoginUserModel {

    String getEmail();

    String getPassword();

    void setEmail(String email);

    void setPassword(String password);

    void setConfirmPassword(String confirmPassword);

    boolean checkUserValidity();
}
