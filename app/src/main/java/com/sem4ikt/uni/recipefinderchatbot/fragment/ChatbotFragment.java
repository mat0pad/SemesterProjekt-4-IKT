package com.sem4ikt.uni.recipefinderchatbot.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ChatListAdapter;
import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.ChatListAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.ChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatListAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.services.Text2Speech;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatbotView;


/**
 * Created by mathiaslykkepedersen on 08/02/2017.
 */

public class ChatbotFragment extends Fragment implements IChatbotView, View.OnClickListener {

    Button sendButton;
    EditText inputField;

    IChatbotPresenter<IChatbotView> chatbotPresenter;

    // Adapter stuff
    ChatListAdapter adapter;
    IChatListAdapterPresenter adapterPresenter;

    // Text 2 speech
    Text2Speech text2Speech;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (container == null)
            return null;


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.chatbot, container, false);

        // Set presenter
        chatbotPresenter = new ChatbotPresenter(this);

        // Text text 2 speech
        chatbotPresenter.doInitText2Speech();

        // Set click lsitener for send button
        sendButton = (Button) view.findViewById(R.id.send_button);
        sendButton.setOnClickListener(this);

        inputField = (EditText) view.findViewById(R.id.message_edittext);

        adapter = new ChatListAdapter(getActivity());

        adapterPresenter = new ChatListAdapterPresenter(adapter);

        ListView listView = (ListView) view.findViewById(R.id.chat_listview);
        listView.setAdapter(adapter);

        // Needed for start up message
        chatbotPresenter.getUser();


        // Set item listenerz
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                adapterPresenter.doClick(position);
            }
        });
        

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
    public void shouldSendButton(boolean shouldEnable) {
        sendButton.setEnabled(shouldEnable);
    }


    @Override
    public void displayNormalMessage(MessageModel msg) {
        adapterPresenter.addMessage(msg);
    }


    // Player functions
    @Override
    public void play(String text2play) {
        text2Speech.playerStart(text2play);
    }

    @Override
    public void initText2Speech() {
        text2Speech = new Text2Speech();
    }


    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (!visible) {
            text2Speech.playerStop();
        }
    }


}
