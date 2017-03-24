package com.sem4ikt.uni.recipefinderchatbot.presenter;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.sem4ikt.uni.recipefinderchatbot.model.ChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.ConversationInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IConversationInteractor;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatbotView;

/**
 * Created by mathiaslykkepedersen on 09/03/2017.
 */

public class ChatbotPresenter extends BasePresenter<IChatbotView> implements IChatbotPresenter<IChatbotView>
{

    private IConversationInteractor api;
    private ChatbotInteractor ci;
    private boolean isInGeneral = true;

    public ChatbotPresenter(IChatbotView view)
    {
        super(view);
        ci = new ChatbotInteractor();
        api = new ConversationInteractor(this);
    }

    public void send(String input)
    {
        view.displayMessage(input, 1);

        ci.message((isInGeneral ? "e665abad-a305-4cf4-a21c-045354782015" : "49630f5e-f2b9-453a-be68-927f17cf64bc"), input)
                .setChatbotListener(new ChatbotInteractor.ChatbotListener()
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
                            api.performAction(response.getOutput().get("action").toString(), response.getInputText());
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
                                    api.performAction(response.getOutput().get("action").toString(), response.getInputText());
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

                    }
                });

    }


    public void showText(String text){

        if(text != null)
            view.displayMessage(text, 0);

        // some error
    }


}
