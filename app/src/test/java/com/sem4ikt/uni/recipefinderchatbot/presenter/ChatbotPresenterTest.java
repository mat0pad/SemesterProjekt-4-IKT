package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IConversationInteractor;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatbotView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by mathiaslykkepedersen on 04/05/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ChatbotPresenterTest {

    @Mock
    IChatbotView view;

    @Mock
    IConversationInteractor api;

    @Mock
    IChatbotInteractor ci;

    @Mock
    IFirebaseDBInteractors.IUserInteractor ui;

    @Mock
    IFirebaseDBInteractors.IMealplanInteractor mi;

    private ChatbotPresenter presenter;

    @Before
    public void setup() throws Exception {

        presenter = new ChatbotPresenter(view, api, ci, ui, mi);
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
