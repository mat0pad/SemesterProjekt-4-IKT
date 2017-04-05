package com.sem4ikt.uni.recipefinderchatbot.services;


import android.util.Log;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.sem4ikt.uni.recipefinderchatbot.model.ChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by henriknielsen on 11/03/2017.
 */


public class ConversationService implements IChatbotService {
    private com.ibm.watson.developer_cloud.conversation.v1.ConversationService convService = new com.ibm.watson.developer_cloud.conversation.v1.ConversationService(com.ibm.watson.developer_cloud.conversation.v1.ConversationService.VERSION_DATE_2016_07_11);
    private ToneAnalyzer toneService = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
    private Map<String, Object> context;
    private String workspaceIdentifier, message;


    public void message(String workspaceId, String msg) {
        workspaceIdentifier = workspaceId;
        message = msg;
    }

    public ConversationService setConversationServiceCredentials(String username, String password){
        convService.setUsernameAndPassword(username, password);

        //Test
        //IFirebaseInteractor fib = new FirebaseInteractor();
        //Log.e("usertest",user.username);

        return this;
    }

    public ConversationService setToneAnalyzerCredentials(String username, String password) {
        toneService.setUsernameAndPassword(username, password);
        return this;
    }




    public void setChatbotListener(final ChatbotInteractor.Callback callback) {
        final String message = this.message;

        toneService.getTone(message, null).enqueue(new ServiceCallback<ToneAnalysis>() {
            @Override
            public void onResponse(ToneAnalysis response) {
                // update context with the tone data returned by the Tone Analyzer
                if (context != null)
                    ToneDetection.updateUserTone(context, response, true);

                MessageRequest newMessage = new MessageRequest.Builder().context(context).inputText(message).build();
                convService.message(workspaceIdentifier, newMessage).enqueue(new ServiceCallback<MessageResponse>() {
                    @Override
                    public void onResponse(MessageResponse response) {
                        context = response.getContext();
                        // Answer is ready
                        callback.onChatbotResponse(ConversationService.this, response);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        callback.onChatbotFailed(ConversationService.this, "Something went wrong, please try again");
                        Log.e("testbotConversation", e.toString());
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                callback.onChatbotFailed(ConversationService.this, "Something went wrong, please try again");
                Log.e("testbotTone", e.toString());
            }
        });
    }

    @Override
    public void setUserContextGeneral(User user) {
        if (user != null) {
            Log.e("tt", "userExist");
            context = new HashMap<>();
            context.put("returning_user", user.returninguser);
            context.put("username", user.username);
        } else {
            context = new HashMap<>();
            context.put("returning_user", false);
            context.put("username", "undefined");
        }
    }
}
