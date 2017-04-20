package com.sem4ikt.uni.recipefinderchatbot.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.activity.LoginActivity;
import com.sem4ikt.uni.recipefinderchatbot.presenter.SettingsPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ISettingsPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ISettingsView;

/**
 * Created by Christian on 12-03-2017.
 */

public class SettingsFragment extends Fragment implements ISettingsView, View.OnClickListener {

    private Button changePass, deleteAcc, confirmPass, cancel;

    private RelativeLayout settingsView;
    private LinearLayout changePassView;

    private EditText passwordField, confirmPasswordField;

    private ISettingsPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (container == null) {
            return null;
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.settings, container, false);

        changePass = (Button) view.findViewById(R.id.change_password_button);
        deleteAcc = (Button) view.findViewById(R.id.delete_account_button);
        confirmPass = (Button) view.findViewById(R.id.confirm_password_button);
        cancel = (Button) view.findViewById(R.id.cancel_button);

        settingsView = (RelativeLayout) view.findViewById(R.id.settings_view);
        changePassView = (LinearLayout) view.findViewById(R.id.change_password_view);

        passwordField = (EditText) view.findViewById(R.id.change_password_input);
        confirmPasswordField = (EditText) view.findViewById(R.id.change_password_input_confirm);

        presenter = new SettingsPresenter(this);


        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.change_password_button:
                presenter.doShowPasswordChangeView();
                break;

            case R.id.delete_account_button:
                // Database, presenter, authorization, whatever
                break;

            case R.id.confirm_password_button:
                presenter.doCheckPassSucess(passwordField.getText().toString(), confirmPasswordField.getText().toString());
                break;

            case R.id.cancel_button:
                presenter.doShowSettingsView();
                break;

            default:
                break;
        }
    }

    @Override
    public void onSwitchToSettingsView() {
        settingsView.setVisibility(View.VISIBLE);
        changePassView.setVisibility(View.GONE);
    }

    @Override
    public void onSwitchToChangePassView() {
        changePassView.setVisibility(View.VISIBLE);
        settingsView.setVisibility(View.GONE);
    }


    @Override
    public void onFinish() {

        Intent intent = new Intent(getActivity(), LoginActivity.class);

        getActivity().startActivity(intent);

        getActivity().finish();
    }

    @Override
    public void onShowToast(String text) {
        Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_LONG);
        toast.show();
    }
}
