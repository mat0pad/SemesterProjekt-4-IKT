package com.sem4ikt.uni.recipefinderchatbot.IntegrationTest.model;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.sem4ikt.uni.recipefinderchatbot.model.ChatbotInteractor;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Henrik on 07-05-2017.
 */

public class ChatbotInteractorIntegrationTest {

    private CountDownLatch signal;
    private ChatbotInteractor mpi = new ChatbotInteractor();
    private String responseStr;

    @Before
    public void setup() throws InterruptedException {

    }

    @Test
    public void chatbotResponse() throws InterruptedException {

        signal = new CountDownLatch(1);


        mpi.message("e665abad-a305-4cf4-a21c-045354782015", "hello").setChatbotListener(new ChatbotInteractor.ChatbotListener() {
            @Override
            public void onChatbotResponse(MessageResponse response) {
                responseStr = response.getOutput().get("text").toString();
                responseStr = responseStr.substring(1, responseStr.length()-1);
                signal.countDown();
            }

            @Override
            public void onChatbotFailed(String errorMsg) {
                responseStr = errorMsg;
                signal.countDown();
            }
        });



        signal.await();

        assertTrue(responseStr.equals("Hey! I'm Botler, what's your name?") ||
                responseStr.equals("Hey you! Botler is my name, what's yours?") ||
                responseStr.equals("Hello! I'm Botler, what's your name?") ||
                responseStr.contains("How are you doing on this nice") ||
                responseStr.equals("Hey , how can I help you today?") ||
                responseStr.equals("What's up! I'm Botler, please tell me your name?") ||
                responseStr.equals("Great to see you . What do you need help with today?") ||
                responseStr.equals("Hi! My human name is Botler. What's yours?") ||
                responseStr.equals("Hello , what's up?") ||
                responseStr.equals("How can I help you today?") ||
                responseStr.equals( "Hi , what's up today?") ||
                responseStr.equals("Hey you! What do you need help with?") ||
                responseStr.equals( "Hi , what's up?") ||
                responseStr.equals("Great to see you again! What do you need today?") ||
                responseStr.equals( "Hello , what's up today?") ||
                (responseStr.contains("I love") && responseStr.contains("How are you doing?")) ||
                responseStr.equals( "Great you're back!  , what can I help you with??") ||
                responseStr.contains("How are you doing on this nice"));
    }
}
