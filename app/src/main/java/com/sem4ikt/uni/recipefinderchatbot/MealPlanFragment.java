package com.sem4ikt.uni.recipefinderchatbot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sem4ikt.uni.recipefinderchatbot.Adapter.ChatListAdapter;

import java.util.Arrays;
import java.util.List;
/**
 * Created by Christian on 12-03-2017.
 */

public class MealPlanFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (container == null) {
            return null;
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.mealplan, container, false);

        return view;
    }
}
