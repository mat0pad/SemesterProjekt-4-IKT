package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.services.ChatbotService;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by henriknielsen on 21/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ChatbotServiceUnitTest {

    @Mock
    private ChatbotInteractor.Callback callback;

    //@InjectMocks
    private ChatbotService cs = new ChatbotService();

    @Before
    public void setup() throws Exception
    {
        cs.setConversationServiceCredentials("f6c68c53-70a5-4a8c-af70-41a5eed85690", "1pMBh1PJOxP0")
                .setToneAnalyzerCredentials("48091cfc-fd99-456a-b67c-00bdeef74b06", "XQE4Xl4oZuk0");
    }

   /* @Test
    public void setChatbotListenerResponse() {
        cs.message("e665abad-a305-4cf4-a21c-045354782015", "dummyMsg");
        cs.setChatbotListener(callback);

        verify(callback, times(1)).onChatbotResponse(any(ChatbotInteractor.Call.class), any(MessageResponse.class));
    }*/
}
