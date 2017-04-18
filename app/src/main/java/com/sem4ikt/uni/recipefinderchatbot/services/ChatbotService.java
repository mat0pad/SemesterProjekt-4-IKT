package com.sem4ikt.uni.recipefinderchatbot.services;


import android.util.Log;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.sem4ikt.uni.recipefinderchatbot.model.ChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by henriknielsen on 11/03/2017.
 */


public class ChatbotService implements IChatbotService {
    private ConversationService convService = new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
    private ToneAnalyzer toneService = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
    private Map<String, Object> contextGeneral, contextRecipe;
    private String workspaceIdentifier, message;


    public void message(String workspaceId, String msg) {
        if (workspaceIdentifier != workspaceId) {
            workspaceIdentifier = workspaceId;

            switch (workspaceId) {
                case "e665abad-a305-4cf4-a21c-045354782015": // Switching to General

                    Object user = contextRecipe.get("username");
                    Object returning = contextRecipe.get("returning_user");
                    Object loop_count = contextRecipe.get("loop_count");

                    if (user != null)
                        contextGeneral.put("username", user);
                    if (returning != null)
                        contextGeneral.put("returning_user", returning);
                    if (loop_count != null)
                        contextGeneral.put("loop_count", loop_count);
                    break;

                case "49630f5e-f2b9-453a-be68-927f17cf64bc": // Switching to Recipe
                    contextRecipe.put("loop_count", contextGeneral.get("loop_count"));
                    contextRecipe.put("username", contextGeneral.get("username"));
                    contextRecipe.put("returning_user", contextGeneral.get("returning_user"));
                    break;
            }
        }

        message = msg;
    }

    public void setUserContextGeneral(User user)
    {
        if (user != null) {
            Log.e("tt", "userExist");
            contextGeneral.put("returning_user", user.returninguser);
            contextGeneral.put("username", user.username);
        } else {
            contextGeneral.put("returning_user", false);
            contextGeneral.put("username", "undefined");
        }

        contextGeneral.put("loop_count", 0);
        contextGeneral.put("num_of_recipes", 0);
        contextGeneral.put("diet", "undefined");
        contextGeneral.put("course", "undefined");
        contextGeneral.put("intolerance", "undefined");
        contextGeneral.put("cuisine", "undefined");

    }

    public ChatbotService setConversationServiceCredentials(String username, String password){
        convService.setUsernameAndPassword(username, password);

        contextRecipe = new HashMap<>();
        contextGeneral = new HashMap<>();

        return this;
    }

    public ChatbotService setToneAnalyzerCredentials(String username, String password) {
        toneService.setUsernameAndPassword(username, password);
        return this;
    }

    public void setChatbotListener(final ChatbotInteractor.Callback callback) {
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

        toneService.getTone(message, null).enqueue(new ServiceCallback<ToneAnalysis>() {
            @Override
            public void onResponse(ToneAnalysis response) {
                // notifyUpdate contextGeneral with the tone data returned by the Tone Analyzer
                ToneDetection.updateUserTone(context, response, true);

                MessageRequest newMessage = new MessageRequest.Builder().context(context).inputText(message).build();
                convService.message(workspaceIdentifier, newMessage).enqueue(new ServiceCallback<MessageResponse>() {
                    @Override
                    public void onResponse(MessageResponse response) {


                        if (Objects.equals(workspaceIdentifier, "e665abad-a305-4cf4-a21c-045354782015")) // General
                            contextGeneral = response.getContext();
                        else if (Objects.equals(workspaceIdentifier, "49630f5e-f2b9-453a-be68-927f17cf64bc")) // Recipe
                            contextRecipe = response.getContext();

                        // Answer is ready
                        callback.onChatbotResponse(ChatbotService.this, response);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        callback.onChatbotFailed(ChatbotService.this, "Something went wrong, please try again");
                        Log.e("botConversation", e.toString());
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                callback.onChatbotFailed(ChatbotService.this, "Something went wrong, please try again");
                Log.e("botTone", e.toString());
            }
        });
    }
}
