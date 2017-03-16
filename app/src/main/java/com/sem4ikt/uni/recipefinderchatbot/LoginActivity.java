package com.sem4ikt.uni.recipefinderchatbot;

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

        System.out.println("Result: " + auth.signIn(email, password));

        /*
        // Show menu
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        // Kill this activity
        finish();*/
    }

    @Override
    public void onClearText() {

    }

    @Override
    public void onSetProgressVisibility() {

    }


}
