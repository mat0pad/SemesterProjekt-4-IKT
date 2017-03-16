package com.sem4ikt.uni.recipefinderchatbot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ILoginView;


/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView {

    Button loginButton;
    EditText emailField, passwordField;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set content view
        setContentView(R.layout.login);

        // Bind views
        passwordField = (EditText) findViewById(R.id.password);
        emailField = (EditText) findViewById(R.id.email);
        loginButton = (Button) findViewById(R.id.email_sign_in_button);

        loginPresenter = new LoginPresenter(this);
    }


    @Override
    public void onSetupFieldListeners() {

    }

    @Override
    public void onLogin() {

    }

    @Override
    public void onClearText() {

    }

    @Override
    public void onSetProgressVisibility() {

    }

}
