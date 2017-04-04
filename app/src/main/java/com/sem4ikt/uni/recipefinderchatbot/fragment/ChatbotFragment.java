package com.sem4ikt.uni.recipefinderchatbot.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.activity.ListRecipeActivity;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ChatListAdapter;
import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.ChatListAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.ChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatListAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatbotView;

import java.util.ArrayList;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (container == null)
            return null;


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.chatbot, container, false);

        // Set presenter
        chatbotPresenter = new ChatbotPresenter(this);

        // Set click lsitener for send button
        sendButton = (Button) view.findViewById(R.id.send_button);
        sendButton.setOnClickListener(this);

        inputField = (EditText) view.findViewById(R.id.message_edittext);

        adapter = new ChatListAdapter(getContext());

        adapterPresenter = new ChatListAdapterPresenter(adapter);

        ListView listView = (ListView) view.findViewById(R.id.chat_listview);
        listView.setAdapter(adapter);

        // Needed for start up message
        //chatbotPresenter.switchWorkspace(0, " ");

        chatbotPresenter.getUser();



        // Showing recipe list view and passing RecipesModels
        Intent intent = new Intent(ChatbotFragment.this.getActivity(), ListRecipeActivity.class);

        final ArrayList<RecipesModel> recipeArray = new ArrayList<>();

        // RecipesModels to pass
        /*RecipesModel model1 = new RecipesModel();
        RecipesModel model2 = new RecipesModel();
        model1.setId(1);
        model2.setId(2);
        model1.setTitle("test title 1");
        model2.setTitle("test title 2");

        // Adding them to List
        recipeArray.add(model1);
        recipeArray.add(model2);

        // Pass the data
        intent.putParcelableArrayListExtra("com.sem4ikt.uni.recipefinderchatbot.fragment.ChatbotFragment.ListOfRecipesModels", recipeArray);

        startActivity(intent);*/

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
    public void displayNormalMessage(MessageModel msg) {
        adapterPresenter.addMessage(msg);
    }

}
