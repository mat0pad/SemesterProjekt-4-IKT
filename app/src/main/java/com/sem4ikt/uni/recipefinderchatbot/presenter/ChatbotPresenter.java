package com.sem4ikt.uni.recipefinderchatbot.presenter;

import android.os.Handler;
import android.os.Looper;

import com.sem4ikt.uni.recipefinderchatbot.model.ChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatbotView;

/**
 * Created by mathiaslykkepedersen on 09/03/2017.
 */

public class ChatbotPresenter extends BasePresenter<IChatbotView> implements IChatbotPresenter<IChatbotView>
{

    private ChatbotInteractor ci;

    public ChatbotPresenter(IChatbotView view)
    {
        super(view);
        ci = new ChatbotInteractor();
    }

    public void send(String input)
    {
        view.displayMessage(input, 1);

        ci.message("e665abad-a305-4cf4-a21c-045354782015", input).setChatbotListener(new ChatbotInteractor.ChatbotListener()
        {
            @Override
            public void onChatbotResponse(final String response)
            {
                Handler mainHandler = new Handler(Looper.getMainLooper());

                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        view.displayMessage(response, 0);
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
}
