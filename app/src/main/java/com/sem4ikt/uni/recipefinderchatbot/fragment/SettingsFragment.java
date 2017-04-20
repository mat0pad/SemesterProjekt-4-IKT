package com.sem4ikt.uni.recipefinderchatbot.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.activity.LoginActivity;
import com.sem4ikt.uni.recipefinderchatbot.presenter.SettingsPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ISettingsPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ISettingsView;

/**
 * Created by Christian on 12-03-2017.
 */

public class SettingsFragment extends Fragment implements ISettingsView, View.OnClickListener{

    private Button changePass;
    private Button deleteAcc;

    private Button confirmPass;
    private Button cancel;

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

        presenter = new SettingsPresenter(this);

        return view;
    }

    @Override
    public void onClick(View v) {
/*
            switch (v.getId()){

                case R.id.change_password:
                    break;

                default:
                    break;
            }
            */
    }


    @Override
    public void onChangePassword() {

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
