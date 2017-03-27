package com.sem4ikt.uni.recipefinderchatbot.model.interfaces;

/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public interface ILoginUserModel {

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    void setConfirmPassword(String confirmPassword);

    boolean checkPasswordsMatches();

    boolean checkUserValidity();
}
