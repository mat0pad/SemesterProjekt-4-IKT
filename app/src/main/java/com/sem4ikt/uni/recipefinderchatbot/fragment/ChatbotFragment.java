package com.sem4ikt.uni.recipefinderchatbot.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.activity.ListDataModelActivity;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ChatListAdapter;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ListContainerFactory;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ListDataContainer;
import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.IngredientsModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.ChatListAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.ChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatListAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.rest.ApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.ISpoonacularAPI;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatbotView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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


        // Set item listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                adapterPresenter.doClick(position);
            }
        });


        // Showing recipe list view and passing RecipesModels
        final Intent intent = new Intent(ChatbotFragment.this.getActivity(), ListDataModelActivity.class);

        ApiClient client = new ApiClient();
        ISpoonacularAPI.ISearch apiService = client.getClient().create(ISpoonacularAPI.ISearch.class);

        Call<List<IngredientsModel>> call = apiService.findByIngredients("Chocolate", 15, false, 1);


        call.enqueue(new Callback<List<IngredientsModel>>() {
            @Override
            public void onResponse(Call<List<IngredientsModel>> call, Response<List<IngredientsModel>> response) {

                if (response.code() == 200) {

                    final ArrayList<IngredientsModel> list = new ArrayList<>();
                    list.addAll(response.body());

                    Log.i("TESTLIST", list.toString());

                    final ListDataContainer container = ListContainerFactory.createIngredientsListContainer(list);

                    Handler mainHandler = new Handler(Looper.getMainLooper());

                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            // Pass the data
                            intent.putExtra("listOfRecipesModels", container);

                            startActivity(intent);
                        }
                    };
                    mainHandler.post(myRunnable);

                } else {
                    // Something went wrong
                }

            }

            @Override
            public void onFailure(Call<List<IngredientsModel>> call, Throwable t) {
                Log.e("TESTLIST", t.toString());
                t.printStackTrace();
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
    public void displayNormalMessage(MessageModel msg) {
        adapterPresenter.addMessage(msg);
    }

}
