package com.sem4ikt.uni.recipefinderchatbot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.List;


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
        View view = inflater.inflate(R.layout.chatbot, container, false);


        List<String> list = Arrays.asList("Hello I'm Botler. How can I help you?", "Hey Botler I need to make some awesome food. The parents are coming over you know", "Okay what kind of food do you need?");

        ChatListAdapter adapter = new ChatListAdapter(list,getContext());

        ListView listView = (ListView) view.findViewById(R.id.chat_listview);

        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        return view;
    }

}
