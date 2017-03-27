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
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setConfirmPassword(String confirmPassword) {this.confirmPassword = confirmPassword;}

    @Override
    public boolean checkPasswordsMatches() {
        return confirmPassword.equals(password);
    }

    @Override
    public boolean checkUserValidity() {

        String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String PASS_REGEX = "((?=.*\\d)(?=.*[a-z]).{6,20})";

        /*
                (			    # Start of group
                (?=.*\d)	    #   must contains one digit from 0-9
                (?=.*[a-z])		#   must contains one lowercase characters
                (?=.*[A-Z])		#   must contains one uppercase characters
                .		        #   match anything with previous condition checking
                {6,20}	        #   length at least 6 characters and maximum of 20
                )			    # End of group

                ?= – means apply the assertion condition, meaningless by itself, always work with other combination
        */

        return password.matches(PASS_REGEX) && email.matches(EMAIL_REGEX);
    }
}
