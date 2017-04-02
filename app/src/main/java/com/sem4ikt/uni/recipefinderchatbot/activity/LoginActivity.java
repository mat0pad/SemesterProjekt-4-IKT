package com.sem4ikt.uni.recipefinderchatbot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ILoginPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ILoginView;


/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    Button loginButton, resetButton, signUpButton;
        EditText emailField, passwordField, confirmPassField;
    ILoginPresenter loginPresenter;
    ProgressBar progressBar;

    // Used to allow back checks
    LoginView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set content view
        setContentView(R.layout.login);

        onPresentView(LoginView.LOGIN);

        // Set button listeners
        loginButton = (Button) findViewById(R.id.sign_in_button);
        resetButton = (Button) findViewById(R.id.reset_password_button);
        signUpButton = (Button) findViewById(R.id.sign_up_button);
        progressBar = (ProgressBar) findViewById(R.id.login_progress);

        TextView noAccountTextView = (TextView) findViewById(R.id.sign_up_text_action);
        TextView forPassTextView = (TextView) findViewById(R.id.forgot_password_text_action);

        forPassTextView.setOnClickListener(this);
        noAccountTextView.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);


        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.sign_in_button:
                loginPresenter.doLogin(emailField.getText().toString(), passwordField.getText().toString());
                break;

            case R.id.reset_password_button:
                loginPresenter.doForgotPassword(emailField.getText().toString());
                break;

            case R.id.sign_up_button:
                loginPresenter.doCreateUser(emailField.getText().toString(), passwordField.getText().toString(), confirmPassField.getText().toString());
                break;

            case R.id.sign_up_text_action:
                loginPresenter.showLayout(LoginView.SIGN_UP);
                break;

            case R.id.forgot_password_text_action:
                loginPresenter.showLayout(LoginView.FORGOT_PASSWORD);
                break;

            default:
                break;
        }
    }

    @Override
    public void onLogin(boolean isSuccessful) {

        if (isSuccessful) {
            // Show menu
            Intent intent = new Intent(this, MainActivity.class);
            //Intent intent = new Intent(this, DetailRecipeActivity.class);
            startActivity(intent);

            // Kill this activity
            finish();
        } else {
            loginPresenter.doToast("Sign In Failed - Try correct credentials");
        }
    }

    @Override
    public void onRegister(boolean isSuccessful) {

        if (isSuccessful) {
            loginPresenter.doToast("Registration Successful - Please check your email to confirm");
        } else {
            loginPresenter.doToast("Registration Failed - Please verify your network connection");
        }
    }

    @Override
    public void onPassForgot(boolean isSuccessful) {

        if (isSuccessful) {
            loginPresenter.doToast("Reset Password Successful - Please check your email to reset it");
        } else {
            loginPresenter.doToast("Reset Password Failed - Please verify your network connection");
        }

    }

    @Override
    public void onPresentView(LoginView v) {


        switch (v) {

            case LOGIN:
                state = LoginView.LOGIN;
                passwordField = (EditText) findViewById(R.id.login_password);
                emailField = (EditText) findViewById(R.id.login_email);

                break;
            case SIGN_UP:
                state = LoginView.SIGN_UP;
                passwordField = (EditText) findViewById(R.id.password_signup);
                emailField = (EditText) findViewById(R.id.email_signup);
                break;

            case FORGOT_PASSWORD:
                state = LoginView.FORGOT_PASSWORD;
                emailField = (EditText) findViewById(R.id.email_reset);
                confirmPassField = (EditText) findViewById(R.id.conf_password);
                passwordField = null;
                break;

            default:
                state = null;
                emailField = null;
                passwordField = null;
                confirmPassField = null;
                break;
        }


        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.email_login_form);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.email_signup_form);
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.password_reset_form);

        linearLayout1.setVisibility((v == LoginView.LOGIN ? View.VISIBLE : View.GONE));
        linearLayout2.setVisibility((v == LoginView.SIGN_UP ? View.VISIBLE : View.GONE));
        linearLayout3.setVisibility((v == LoginView.FORGOT_PASSWORD ? View.VISIBLE : View.GONE));
    }

    @Override
    public void onShowToast(String text) {
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onClearText() {

        if (emailField != null)
            emailField.getText().clear();

        if (passwordField != null)
        passwordField.getText().clear();
    }

    @Override
    public void onSetProgressVisibility(boolean visible) {

        if (visible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void onBackPressed() {
        loginPresenter.doBack(state);
    }

    @Override
    public void onFinish() {
        finish();
    }

    public enum LoginView {LOGIN, SIGN_UP, FORGOT_PASSWORD}
}
