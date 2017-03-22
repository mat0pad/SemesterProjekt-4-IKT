package com.sem4ikt.uni.recipefinderchatbot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ILoginPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ILoginView;


/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    Button loginButton;
    EditText emailField, passwordField;
    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set content view
        setContentView(R.layout.login);

        // Bind views
        passwordField = (EditText) findViewById(R.id.password);
        emailField = (EditText) findViewById(R.id.email);
        loginButton = (Button) findViewById(R.id.sign_in_button);
        loginButton.setOnClickListener(this);

        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.sign_in_button:
                onLogin(true);
                //loginPresenter.doLogin(emailField.getText().toString(), passwordField.getText().toString());
                break;

            case R.id.sign_up:
                onRegister(true);
                break;

            case R.id.sign_up_button:
                onAccCreated(true);
                break;

            case R.id.restore_password:
                onPassForgot(true);
                break;

            case R.id.reset_password_button:
                onPassSend(true);
                break;

            default:
                break;
        }

    }


    @Override
    public void onLogin(boolean isSuccessful) {
        // Show menu
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        // Kill this activity
        finish();
    }

    @Override
    public void onRegister(boolean isSuccessful) {
        LinearLayout email_signup_form = (LinearLayout) findViewById(R.id.email_signup_form);
        LinearLayout email_login_form = (LinearLayout) findViewById(R.id.email_login_form);

        email_login_form.setVisibility(View.GONE);
        email_signup_form.setVisibility(View.VISIBLE);
    }

    public void onAccCreated(boolean isSuccessful) {
        //create account

        LinearLayout email_signup_form = (LinearLayout) findViewById(R.id.email_signup_form);
        LinearLayout email_login_form = (LinearLayout) findViewById(R.id.email_login_form);

        email_signup_form.setVisibility(View.GONE);
        email_login_form.setVisibility(View.VISIBLE);
    }


    @Override
    public void onPassForgot(boolean isSuccessful) {
        LinearLayout password_reset_form = (LinearLayout) findViewById(R.id.password_reset_form);
        LinearLayout email_login_form = (LinearLayout) findViewById(R.id.email_login_form);

        email_login_form.setVisibility(View.GONE);
        password_reset_form.setVisibility(View.VISIBLE);

    }

    public void onPassSend(boolean isSuccessful) {
        //Send email

        LinearLayout password_reset_form = (LinearLayout) findViewById(R.id.password_reset_form);
        LinearLayout email_login_form = (LinearLayout) findViewById(R.id.email_login_form);

        password_reset_form.setVisibility(View.GONE);
        email_login_form.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClearText() {

        emailField.getText().clear();
        passwordField.getText().clear();
    }


    @Override
    public void onSetProgressVisibility(boolean visible) {


    }


}
