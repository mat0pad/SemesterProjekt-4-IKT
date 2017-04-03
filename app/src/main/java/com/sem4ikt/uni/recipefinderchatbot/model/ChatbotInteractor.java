package com.sem4ikt.uni.recipefinderchatbot.model;


import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.services.ChatbotService;
import com.sem4ikt.uni.recipefinderchatbot.services.IChatbotService;

/**
 * Created by henriknielsen on 16/03/2017.
 */

public class ChatbotInteractor implements IChatbotInteractor
{

    private IChatbotService cs = new ChatbotService();


    public ChatbotInteractor() {
        cs.setConversationServiceCredentials("f6c68c53-70a5-4a8c-af70-41a5eed85690", "1pMBh1PJOxP0")
                .setToneAnalyzerCredentials("48091cfc-fd99-456a-b67c-00bdeef74b06", "XQE4Xl4oZuk0");

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
                        System.out.println(response.getContext().toString());
                        listener.onChatbotResponse(response);
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


    public interface ChatbotListener {
        void onChatbotResponse(MessageResponse response);
        void onChatbotFailed(String errorMsg);
    }

    public interface ChatbotCall {
        void setChatbotListener(ChatbotListener listener);
    }

    public interface Call {
        void setChatbotListener(Callback responseCallback);
    }

    public interface Callback {
        void onChatbotResponse(Call call, MessageResponse response);
        void onChatbotFailed(Call call, String errorMsg);
    }
}
