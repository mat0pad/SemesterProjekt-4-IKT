package com.sem4ikt.uni.recipefinderchatbot.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ChatListAdapter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.ChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatbotView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mathiaslykkepedersen on 08/02/2017.
 */

public class ChatbotFragment extends Fragment implements IChatbotView, View.OnClickListener {

    Button sendButton;
    EditText inputField;
    ChatListAdapter adapter;

    private IChatbotPresenter<IChatbotView> chatbotPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (container == null) {
            return null;
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.chatbot, container, false);

        chatbotPresenter = new ChatbotPresenter(this);

        sendButton = (Button) view.findViewById(R.id.send_button);
        sendButton.setOnClickListener(this);

        inputField = (EditText) view.findViewById(R.id.message_edittext);

        List<Pair<String, Integer>> list = new ArrayList<>();

        adapter = new ChatListAdapter(list,getContext());

        ListView listView = (ListView) view.findViewById(R.id.chat_listview);
        listView.setAdapter(adapter);

        // returning user
        //chatbotPresenter.switchWorkspace(0, "123123123123123returningUser");
        // new user
        chatbotPresenter.switchWorkspace(0, " ");

        return view;
    }


    public void onClick(View v){

        switch (v.getId()){

            case R.id.send_button:

                chatbotPresenter.send(inputField.getText().toString());
                inputField.getText().clear();

                break;

            default:
                break;
        }

    }


    @Override
    public void displayMessage(String input, int direction) {
        adapter.adapterPresenter.addMessage(input, direction);
    }

}
