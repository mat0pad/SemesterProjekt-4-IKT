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
@RunWith(MockitoJUnitRunner.class)
public class ChatListAdapterPresenterTest {
    @Mock
    IChatListAdapterView view;

    private ChatListAdapterPresenter presenter;


    @Before
    public void setup() throws Exception {

        presenter = new ChatListAdapterPresenter(view);
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

    @Test
    public void addMessageTest()
    {
        MessageModel m = new MessageModel("",0, MessageModel.TYPE.NORMAL);

        presenter.addMessage(m);
        verify(view,times(1)).onAddMessage(m);
    }

    @Test
    public void doClickTest()
    {
        int position = 0;

        presenter.doClick(position);

        verify(view,times(1)).onClick(position);
    }
}

