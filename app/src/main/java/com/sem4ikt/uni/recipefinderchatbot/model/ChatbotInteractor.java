package com.sem4ikt.uni.recipefinderchatbot.model;


import android.view.View;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

/**
 * Created by henriknielsen on 16/03/2017.
 */

public class ChatbotInteractor implements IChatbotInteractor
{

    private ConversationService cs = new ConversationService();
    ;


    public interface ChatbotListener
    {
        void onChatbotResponse(String response);
        void onChatbotFailed(String errorMsg);
    }

    public interface ChatbotCall
    {
        void setChatbotListener(ChatbotListener listener);
    }


    interface Call
    {
        void setChatbotListener(Callback responseCallback);
    }

    interface Callback
    {
        void onChatbotResponse(Call call, MessageResponse response);
        void onChatbotFailed(Call call, String errorMsg);
    }


    public ChatbotInteractor()
    {
        cs.setConversationUsernameAndPassword("f6c68c53-70a5-4a8c-af70-41a5eed85690", "1pMBh1PJOxP0").setToneAnalyzerUsernameAndPassword("48091cfc-fd99-456a-b67c-00bdeef74b06", "XQE4Xl4oZuk0");
    }


    @Override
    public ChatbotCall message(String workspaceId, String msg)
    {

        cs.message(workspaceId, msg);


        final Call call = cs;

        return new ChatbotCall()
        {
            @Override
            public void setChatbotListener(final ChatbotListener listener)
            {
                call.setChatbotListener(new Callback()
                {
                    @Override
                    public void onChatbotResponse(Call call, MessageResponse response)
                    {
                        String s = response.getOutput().get("text").toString();
                        listener.onChatbotResponse(s.substring(1, s.length()-1));
                    }

                    @Override
                    public void onChatbotFailed(Call call, String errorMsg)
                    {
                        listener.onChatbotFailed(errorMsg);
                    }
                });
            }
        };
    }
}
