package com.sem4ikt.uni.recipefinderchatbot.model;


import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatbotView;

import java.util.Map;

import jersey.repackaged.jsr166e.CompletableFuture;

import static android.content.ContentValues.TAG;


/**
 * Created by henriknielsen on 11/03/2017.
 */


public class ConversationService implements IConversationService, ChatbotInteractor.Call
{
    private com.ibm.watson.developer_cloud.conversation.v1.ConversationService convService = new com.ibm.watson.developer_cloud.conversation.v1.ConversationService(com.ibm.watson.developer_cloud.conversation.v1.ConversationService.VERSION_DATE_2016_07_11);
    private ToneAnalyzer toneService = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
    private Map<String, Object> context;
    private String workspaceIdentifier, message;


    public void message(String workspaceId, String msg)
    {
        workspaceIdentifier = workspaceId;
        message = msg;
    }

    public ConversationService setConversationUsernameAndPassword(String username, String password)
    {
        convService.setUsernameAndPassword(username, password);
        return this;
    }

    public ConversationService setToneAnalyzerUsernameAndPassword(String username, String password)
    {
        toneService.setUsernameAndPassword(username, password);
        return this;
    }


    public void setChatbotListener(ChatbotInteractor.Callback callback)
    {

        final ChatbotInteractor.Callback callback1 = callback;

        toneService.getTone(message, null).enqueue(new ServiceCallback<ToneAnalysis>()
        {
            @Override
            public void onResponse(ToneAnalysis response)
            {
                // update context with the tone data returned by the Tone Analyzer
                if (context != null)
                    ToneDetection.updateUserTone(context, response, true);

                MessageRequest newMessage = new MessageRequest.Builder().context(context).inputText(message).build();
                convService.message(workspaceIdentifier, newMessage).enqueue(new ServiceCallback<MessageResponse>()
                {
                    @Override
                    public void onResponse(MessageResponse response)
                    {
                        context = response.getContext();
                        // Answer is ready
                        callback1.onChatbotResponse(ConversationService.this, response);
                    }

                    @Override
                    public void onFailure(Exception e)
                    {
                        callback1.onChatbotFailed(ConversationService.this, "Something went wrong, please try again");
                        Log.e("testbotConversation", e.toString());
                    }
                });
            }

            @Override
            public void onFailure(Exception e)
            {
                callback1.onChatbotFailed(ConversationService.this, "Something went wrong, please try again");
                Log.e("testbotTone", e.toString());
            }
        });
    }
}
