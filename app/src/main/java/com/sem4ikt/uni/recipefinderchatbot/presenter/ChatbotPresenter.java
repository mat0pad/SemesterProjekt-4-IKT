package com.sem4ikt.uni.recipefinderchatbot.presenter;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ChatListAdapter;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.database.UserInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.ChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.ConversationInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.MoreRecipeMessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.SingleRecipeMessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IConversationInteractor;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatbotView;

/**
 * Created by mathiaslykkepedersen on 09/03/2017.
 */

public class ChatbotPresenter extends BasePresenter<IChatbotView> implements IChatbotPresenter<IChatbotView>
{

    private IConversationInteractor api;
    private IChatbotInteractor ci;
    private IFirebaseDBInteractors.IUserInteractor ui;
    private boolean isInGeneral = true;

    public ChatbotPresenter(IChatbotView view){
        super(view);
        api = new ConversationInteractor(this);
        ui = new UserInteractor(this);
        ci = new ChatbotInteractor();
    }

    @Override
    public void send(String input)
    {
        view.displayNormalMessage(new MessageModel(input, ChatListAdapter.DIRECTION_OUTGOING, MessageModel.TYPE.NORMAL));


        ci.message((isInGeneral ? "e665abad-a305-4cf4-a21c-045354782015" : "49630f5e-f2b9-453a-be68-927f17cf64bc"), input).setChatbotListener(new ChatbotInteractor.ChatbotListener()
        {
            @Override
            public void onChatbotResponse(final MessageResponse response)
            {
                Handler mainHandler = new Handler(Looper.getMainLooper());

                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {

                        if (response.getOutput().containsKey("action")){
                            System.out.println("Action found!");
                            api.performAction(response.getOutput().get("action").toString(), response);
                        }
                        else if (response.getOutput().containsKey("goTo")){

                           isInGeneral = !isInGeneral;

                            System.out.println(response.toString());

                            api.switchWorkspace(response.getOutput().get("goTo").toString(), response.getInputText());
                        }
                        else{
                            System.out.println("ElSE CASE");
                            System.out.println(response);
                            showText(response.getText().toString()
                                    .substring(1, response.getText().toString().length()-1));
                        }
                    }
                };
                mainHandler.post(myRunnable);
            }

            @Override
            public void onChatbotFailed(String errorMsg)
            {

            }
        });
    }

    @Override
    public void switchWorkspace(int spaceId, String lastInput) {

        ci.message((spaceId == 0 ? "e665abad-a305-4cf4-a21c-045354782015" : "49630f5e-f2b9-453a-be68-927f17cf64bc"), lastInput) // 0 = Generel, 1 = rR
                .setChatbotListener(new ChatbotInteractor.ChatbotListener()
                {
                    @Override
                    public void onChatbotResponse(final MessageResponse response)
                    {
                        Handler mainHandler = new Handler(Looper.getMainLooper());

                        Runnable myRunnable = new Runnable() {
                            @Override
                            public void run() {

                                System.out.println(response.toString());

                                if(response.getOutput().containsKey("action")) {
                                    api.performAction(response.getOutput().get("action").toString(), response);
                                }
                                else if (response.getOutput().containsKey("goTo")){

                                    isInGeneral = !isInGeneral;
                                    Log.e("test",response.getInputText());

                                    api.switchWorkspace(response.getOutput().get("goTo").toString(), response.getInputText());
                                }
                                else {
                                    showText(response.getText().toString()
                                            .substring(1, response.getText().toString().length() - 1));
                                }

                            }
                        };
                        mainHandler.post(myRunnable);
                    }

                    @Override
                    public void onChatbotFailed(String errorMsg)
                    {
                        System.out.println(errorMsg);
                    }
                });

    }

    @Override
    public void showErrorText() {
        view.displayNormalMessage(new MessageModel("An error occurred. Please try again.", ChatListAdapter.DIRECTION_INCOMING, MessageModel.TYPE.NORMAL));
    }

    @Override
    public void showText(String msg) {

        if (msg != null)
            view.displayNormalMessage(new MessageModel(msg, ChatListAdapter.DIRECTION_INCOMING, MessageModel.TYPE.NORMAL));

        else
            showErrorText();
    }

    @Override
    public void showSingleRecipeText(String msg, String img, int id) {

        if (msg != null)
            view.displayNormalMessage(new SingleRecipeMessageModel(msg, ChatListAdapter.DIRECTION_INCOMING, img, id));

        else
            showErrorText();
    }

    @Override
    public void showMoreRecipesText(String msg, String img, Object obj, MessageModel.TYPE type) {

        if (msg != null)
            view.displayNormalMessage(new MoreRecipeMessageModel(msg, ChatListAdapter.DIRECTION_INCOMING, img, obj, type));

        else
            showErrorText();
    }



    @Override
    public void getUser() {
        ui.getUser();
    }

    @Override
    public void onReceived(User user) {
        if(user == null) {
            ui.addUser(new User());
        }
        ci.setContext(user);
        switchWorkspace(0, " ");
    }

}
