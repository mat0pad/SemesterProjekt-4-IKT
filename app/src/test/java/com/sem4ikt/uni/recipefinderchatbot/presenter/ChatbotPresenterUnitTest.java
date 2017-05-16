package com.sem4ikt.uni.recipefinderchatbot.presenter;

import android.os.Handler;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackUser;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.ChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.MoreRecipeMessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.SingleRecipeMessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IConversationInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatbotView;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by anton on 04-05-2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ChatbotPresenterUnitTest {

    private  ChatbotPresenter presenter;

    private static final String KEY = "text";

    private boolean isLopping = false;

    private CountDownLatch signal;

    @Mock
    IConversationInteractor convInteractor;

    @Mock
    IChatbotInteractor chatbotInteractor;

    @Mock
    IFirebaseDBInteractors.IUserInteractor userInteractor;

    @Mock
    IFirebaseDBInteractors.IMealplanInteractor mealplanInteractor;

    @Mock
    IChatbotView view;

    @Mock
    Handler handler;

    @Mock
    ChatbotInteractor.ChatbotCall call;


    @Before
    public void setup()
    {
        presenter = new ChatbotPresenter(view,convInteractor,userInteractor,chatbotInteractor,mealplanInteractor,handler);
    }



    @Test
    public void SendTestViewShoulSendButton()
    {

        String input = "test";

        when(chatbotInteractor.message("e665abad-a305-4cf4-a21c-045354782015",input)).thenReturn(new ChatbotInteractor.ChatbotCall() {
            @Override
            public void setChatbotListener(ChatbotInteractor.ChatbotListener listener) {
                listener.onChatbotResponse(new MessageResponse());

            }
        });

        presenter.send(input);
        verify(view,times(1)).shouldSendButton(false);
    }

    @Test //TT
    public void SendTestViewHandleAction() throws InterruptedException {
        /*
        signal = new CountDownLatch(1);

        String input = "test";
       final MessageResponse response = new MessageResponse();
        Map<String,Object> output = new HashMap<>();
        output.put("action",input);
        response.setOutput(output);

        when(chatbotInteractor.message("e665abad-a305-4cf4-a21c-045354782015",input)).thenReturn(call);

        //when(call.setChatbotListener(ChatbotInteractor.ChatbotListener);
        //when(call.setChatbotListener(any())).thenReturn(response);

        presenter.send(input);

        Thread.sleep(3);
       //signal.await();

        verify(convInteractor,times(1)).performAction(anyString(),any(MessageResponse.class));
*/
    }

    @Test
    public void SendTestViewDisplayNormalMessage() {
        String input = "test";

        when(chatbotInteractor.message("e665abad-a305-4cf4-a21c-045354782015", input)).thenReturn(new ChatbotInteractor.ChatbotCall() {
            @Override
            public void setChatbotListener(ChatbotInteractor.ChatbotListener listener) {
                listener.onChatbotResponse(new MessageResponse());

            }
        });



        presenter.send(input);

        verify(view, times(1)).displayNormalMessage(any(MessageModel.class));
    }

    @Test
    public void switchWorkspaceChatbotDoMessage()
    {
        String input = "test";

        String workspaceId = "e665abad-a305-4cf4-a21c-045354782015";

        when(chatbotInteractor.message(workspaceId, input)).thenReturn(new ChatbotInteractor.ChatbotCall() {
            @Override
            public void setChatbotListener(ChatbotInteractor.ChatbotListener listener) {
                listener.onChatbotResponse(new MessageResponse());

            }
        });

        presenter.switchWorkspace(0,input);

        verify(chatbotInteractor,times(1)).message(workspaceId,input);
    }

    @Test
    public void ShowErrorTextTestViewCall()
    {
        presenter.showErrorText();

        verify(view,times(1)).play(anyString());
        verify(view,times(1)).displayNormalMessage(any(MessageModel.class));
        verify(view,times(1)).shouldSendButton(true);
    }

    @Test
    public void showTextTestViewNullParameter()
    {
        presenter.showText(null);

        verify(view,times(2)).shouldSendButton(true);
    }

    @Test
    public void showTextTestViewStringParameter()
    {
        String msg = "test";

        presenter.showText(msg);

        verify(view,times(1)).play(msg);
        verify(view,times(1)).displayNormalMessage(any(MessageModel.class));
    }

    @Test
    public void showSingleRecipeTextTestNullParameter()
    {
        presenter.showSingleRecipeText(null,null,0);

        verify(view,times(2)).shouldSendButton(true);

    }

    @Test
    public void showSingleRecipeTextTestViewCall()
    {
        String msg = "test";


        presenter.showSingleRecipeText(msg, "",0);

        verify(view,times(1)).play(msg);
        verify(view,times(1)).displayNormalMessage(any(SingleRecipeMessageModel.class));
    }

    @Test
    public void showMoreRecipesTextNullParamterViewCall()
    {
        presenter.showMoreRecipesText(null,null,null, MessageModel.TYPE.NORMAL);

        verify(view,times(2)).shouldSendButton(true);
    }

    @Test
    public void showMoreRecipesTextWithParamterViewCall()
    {
        String msg = "test";

        presenter.showMoreRecipesText(msg,"",null, MessageModel.TYPE.NORMAL);

        verify(view,times(1)).play(msg);
        verify(view,times(1)).displayNormalMessage(any(MoreRecipeMessageModel.class));
    }

    @Test
    public void getUserTestCallUserInteractor()
    {
        presenter.getUser();

        verify(userInteractor,times(1)).getUser(presenter);
    }

    @Test
    public void doInitText2SpeechTestCallView()
    {
        presenter.doInitText2Speech();

        verify(view,times(1)).initText2Speech();
    }

    @Test
    public void updateUserTestCallUserInteractor()
    {
        String name = "test";
        String response = "response";

        presenter.updateUser(name,response);

        verify(userInteractor,times(1)).updateUser(name,true);
        verify(view,times(1)).play(response);
    }

    @Test
    public void addMealPlanWeekTestMealplanInteractor()
    {
        MealPlanWeekModel mp = new MealPlanWeekModel();
        Date  date = new Date();

        presenter.addMealPlanWeek(mp,date);

        verify(mealplanInteractor,times(1)).addMealPlanWeek(mp,date,presenter);
    }

    @Test
    public void addMealPlanDayTestMealplanInteractor()
    {
        MealPlanDayModel mp = new MealPlanDayModel();
        Date  date = new Date();

        presenter.addMealPlanDay(mp,date);

        verify(mealplanInteractor,times(1)).addMealPlanDay(mp,date,presenter);
    }

    @Test
    public void onReceivedTestUSERFOUND()
    {
        User user = new User();

        String workspaceid = "e665abad-a305-4cf4-a21c-045354782015";


        when(chatbotInteractor.message(workspaceid, " ")).thenReturn(new ChatbotInteractor.ChatbotCall() {
            @Override
            public void setChatbotListener(ChatbotInteractor.ChatbotListener listener) {
                listener.onChatbotResponse(new MessageResponse());

            }
        });

        presenter.onReceived(user, ICallbackUser.UserCallbackType.USER_FOUND);

        verify(chatbotInteractor,times(1)).setContext(user);

    }

    @Test
    public void onReceivedTestUSERNOTFOUND()
    {
        User user = new User();

        String workspaceid = "e665abad-a305-4cf4-a21c-045354782015";


        when(chatbotInteractor.message(workspaceid, " ")).thenReturn(new ChatbotInteractor.ChatbotCall() {
            @Override
            public void setChatbotListener(ChatbotInteractor.ChatbotListener listener) {
                listener.onChatbotResponse(new MessageResponse());

            }
        });

        presenter.onReceived(user, ICallbackUser.UserCallbackType.USER_NOT_FOUND);

        verify(chatbotInteractor,times(1)).setContext(null);
        verify(userInteractor,times(1)).addUser(any(User.class));
    }

    @Test
    public void onFailureTestChatbotInteractorCall()
    {


        String workspaceid = "e665abad-a305-4cf4-a21c-045354782015";


        when(chatbotInteractor.message(workspaceid, " ")).thenReturn(new ChatbotInteractor.ChatbotCall() {
            @Override
            public void setChatbotListener(ChatbotInteractor.ChatbotListener listener) {
                listener.onChatbotResponse(new MessageResponse());

            }
        });

        presenter.onFailure();

        verify(chatbotInteractor,times(1)).setContext(null);
    }

    @Test
    public void onReceivedMealPlanAddTestViewCall()
    {
        presenter.onReceivedMealPlanAdd();

        verify(view,times(1)).play(anyString());

    }

    @Test
    public void onFailureMealPlanAddTestViewCall()
    {
        presenter.onFailureMealPlanAdd();

        verify(view,times(1)).play(anyString());

    }

    @Test
    public void clearViewOnDestroy() {
        presenter.clearView();

        Assert.assertEquals(presenter.getView(), null);
    }

    @Test
    public void setView() {
        presenter.clearView();
        presenter.setView(view);

        Assert.assertEquals(presenter.getView(), view);
    }


}
