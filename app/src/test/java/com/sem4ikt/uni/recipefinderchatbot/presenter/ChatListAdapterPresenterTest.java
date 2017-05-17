package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatListAdapterView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by mathiaslykkepedersen on 04/05/2017.
 */
@RunWith(MockitoJUnitRunner.class) //Includes mockito in unittestClass
public class ChatListAdapterPresenterTest {


    @Mock  //<-- Mocking with mockito
    IChatListAdapterView view; //Class that gets mocked

    private ChatListAdapterPresenter presenter; //class we want to unittest

    @Before
    public void setup() throws Exception {
        presenter = new ChatListAdapterPresenter(view); //Injecting the mocke class
    }

    @Test //Junit unittest annotation
    public void clearViewOnDestroy() {
        presenter.clearView(); //Call method

        Assert.assertEquals(presenter.getView(), null); //Assert with Junit
    }
    @Test
    public void addMessageTest()
    {
        MessageModel m = new MessageModel("",0, MessageModel.TYPE.NORMAL);

        presenter.addMessage(m); //Call Method
        //Static method verify from Mockijito, check if the mocked view
        //Received 1 call on the method onAddMessage with parameter m
        verify(view,times(1)).onAddMessage(m);
    }


    @Test
    public void setView() {
        presenter.clearView();
        presenter.setView(view);

        Assert.assertEquals(presenter.getView(), view);
    }



    @Test
    public void doClickTest()
    {
        int position = 0;

        presenter.doClick(position);

        verify(view,times(1)).onClick(position);
    }
}

