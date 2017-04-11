package com.sem4ikt.uni.recipefinderchatbot.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ISettingsPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ISettingsView;

/**
 * Created by Christian on 12-03-2017.
 */

public class SettingsFragment extends Fragment implements ISettingsView, View.OnClickListener{

    Button resetButton;

    ISettingsPresenter<ISettingsView> settingsPresenter;

    //SettingsView state;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (container == null) {
            return null;
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.settings, container, false);

        return view;
    }

    @Override
    public void onClick(View v) {

            switch (v.getId()){

                case R.id.change_password:



                    break;

                default:
                    break;
            }

    }

    @Override
    public void onPresentView() {




    }

    @Override
    public void onChangePassword() {

    }
}
