package com.sem4ikt.uni.recipefinderchatbot.services;


import android.util.Log;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.sem4ikt.uni.recipefinderchatbot.model.ChatbotInteractor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by henriknielsen on 11/03/2017.
 */


public class ChatbotService implements IChatbotService
{
    private com.ibm.watson.developer_cloud.conversation.v1.ConversationService convService = new com.ibm.watson.developer_cloud.conversation.v1.ConversationService(com.ibm.watson.developer_cloud.conversation.v1.ConversationService.VERSION_DATE_2016_07_11);
    private ToneAnalyzer toneService = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
    private Map<String, Object> contextGeneral, contextRecipe;
    private String workspaceIdentifier, message;


    public void message(String workspaceId, String msg)
    {
        if(workspaceIdentifier != workspaceId) {
            workspaceIdentifier = workspaceId;

            switch (workspaceId) {
                case "e665abad-a305-4cf4-a21c-045354782015": // Switching to General

                    contextGeneral.put("username" ,contextRecipe.get("username"));
                    contextGeneral.put("returning_user", contextRecipe.get("returning_user"));
                    break;

                case "49630f5e-f2b9-453a-be68-927f17cf64bc": // Switching to Recipe

                    contextRecipe.put("username" ,contextGeneral.get("username"));
                    contextRecipe.put("returning_user", contextGeneral.get("returning_user"));
                    break;
            }
        }

        message = msg;
    }

    public ChatbotService setConversationServiceCredentials(String username, String password)
    {
        convService.setUsernameAndPassword(username, password);

        contextRecipe = new HashMap<>();
        contextGeneral = new HashMap<>();
        contextGeneral.put("returning_user", false);
        contextGeneral.put("username", "undefined");

        return this;
    }

    public ChatbotService setToneAnalyzerCredentials(String username, String password)
    {
        toneService.setUsernameAndPassword(username, password);
        return this;
    }


    public void setChatbotListener(final ChatbotInteractor.Callback callback)
    {
        final String message = this.message;
        final String workspaceIdentifier = this.workspaceIdentifier;

        Map<String, Object> ifContext = new HashMap<>();

        switch (workspaceIdentifier) {
            case "e665abad-a305-4cf4-a21c-045354782015": // General
                ifContext = contextGeneral;
                break;

            case "49630f5e-f2b9-453a-be68-927f17cf64bc": // Recipe
                ifContext = contextRecipe;
                break;
        }

        final Map<String, Object> context = ifContext;

        toneService.getTone(message, null).enqueue(new ServiceCallback<ToneAnalysis>()
        {
            @Override
            public void onResponse(ToneAnalysis response)
            {
                // update contextGeneral with the tone data returned by the Tone Analyzer
                ToneDetection.updateUserTone(context, response, true);

                MessageRequest newMessage = new MessageRequest.Builder().context(context).inputText(message).build();
                convService.message(workspaceIdentifier, newMessage).enqueue(new ServiceCallback<MessageResponse>()
                {
                    @Override
                    public void onResponse(MessageResponse response)
                    {
                        if(Objects.equals(workspaceIdentifier, "e665abad-a305-4cf4-a21c-045354782015")) // General
                            contextGeneral = response.getContext();
                        else if(Objects.equals(workspaceIdentifier, "49630f5e-f2b9-453a-be68-927f17cf64bc")) // Recipe
                            contextRecipe = response.getContext();

                        // Answer is ready
                        callback.onChatbotResponse(ChatbotService.this, response);
                    }

                    @Override
                    public void onFailure(Exception e)
                    {
                        callback.onChatbotFailed(ChatbotService.this, "Something went wrong, please try again");
                        Log.e("botConversation", e.toString());
                    }
                });
            }

            @Override
            public void onFailure(Exception e)
            {
                callback.onChatbotFailed(ChatbotService.this, "Something went wrong, please try again");
                Log.e("botTone", e.toString());
            }
        });
    }
}
