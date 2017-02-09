package com.sem4ikt.uni.recipefinderchatbot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;


/**
 * Created by mathiaslykkepedersen on 08/02/2017.
 */

public class ChatbotFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (container == null) {
            return null;
        }
        // Inflate the layout for this fragment
        View view = (RelativeLayout) inflater.inflate(R.layout.chatbot, container, false);

        return view;
    }

}
