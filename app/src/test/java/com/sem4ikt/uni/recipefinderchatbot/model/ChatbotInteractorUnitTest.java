package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.services.IConversationService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by henriknielsen on 21/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ChatbotInteractorUnitTest {

    @Mock
    IConversationService cs;

    @Mock
    ChatbotInteractor.ChatbotListener listener;

    @InjectMocks
    private ChatbotInteractor ci = new ChatbotInteractor();

    @Test
    public void setChatbotListener() {
        ci.message("dummyId", "dummyMessage").setChatbotListener(any(ChatbotInteractor.ChatbotListener.class));

        verify(cs, times(1)).setChatbotListener(any(ChatbotInteractor.Callback.class));
    }
}
