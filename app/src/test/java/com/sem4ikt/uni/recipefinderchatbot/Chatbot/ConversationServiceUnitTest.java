package com.sem4ikt.uni.recipefinderchatbot.Chatbot;

/**
 * Created by henriknielsen on 21/03/2017.
 */

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.sem4ikt.uni.recipefinderchatbot.model.ChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.services.ConversationService;
import com.sem4ikt.uni.recipefinderchatbot.services.IConversationService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConversationServiceUnitTest {

    @Mock
    private ChatbotInteractor.Callback callback;

    @InjectMocks
    private ConversationService cs = new ConversationService();


    @Test
    public void setChatbotListenerResponse() {
        cs.setChatbotListener(callback);

        verify(callback, times(1)).onChatbotResponse(any(ChatbotInteractor.Call.class), any(MessageResponse.class));
    }
}
