package com.sem4ikt.uni.recipefinderchatbot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sem4ikt.uni.recipefinderchatbot.database.Authentication;
import com.sem4ikt.uni.recipefinderchatbot.presenter.LoginPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ILoginView;


/**
 * Created by mathiaslykkepedersen on 16/03/2017.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    Button loginButton;
    EditText emailField, passwordField;
    LoginPresenter loginPresenter;
    Authentication auth;

    public enum AUTH{

        SIGN_IN_SUCCESS,
        SIGN_IN_FAILED,
        CREATE_SUCCESS,
        CREATE_FAILED,
        FORGOT_PASSWORD_SUCCESS,
        FORGOT_PASSWORD_FAILED
    }

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

        auth = new Authentication(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.sign_in_button:
                loginPresenter.doLogin(emailField.getText().toString(), passwordField.getText().toString());
                break;

            default:
                break;
        }

    }

    @Override
    public void onLogin(String email, String password) {
        auth.signIn(email,password);
    }

    @Override
    public void showMainActivity() {
        // Show menu
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        // Kill this activity
        finish();
    }


    @Override
    public void onClearText() {

    }

    public void authenticationHandler(AUTH auth, String reason) {

        System.out.println("Authentication result: " + reason);

        switch (auth){

            case SIGN_IN_SUCCESS:
                loginPresenter.signInResult(true);
                break;
            case SIGN_IN_FAILED:
                loginPresenter.signInResult(false);
                break;
            case CREATE_SUCCESS:
                loginPresenter.createUserResult(true);
                break;
            case CREATE_FAILED:
                loginPresenter.createUserResult(false);
                break;
            case FORGOT_PASSWORD_SUCCESS:
                loginPresenter.forgotPasswordResult(true);
                break;
            case FORGOT_PASSWORD_FAILED:
                loginPresenter.forgotPasswordResult(false);
                break;

            default:
                break;
        }
    }


    @Override
    public void onSetProgressVisibility() {

    }


}
