package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;

/**
 * Created by mathiaslykkepedersen on 09/03/2017.
 */

public interface IChatbotPresenter<V> extends IBasePresenter<V> {

    void send(String input);
    void switchWorkspace(int spaceId, String lastInput);

    void showErrorText();
    void showText(String text);

    void showSingleRecipeText(String msg, String img, int id);
    void showMoreRecipesText(String msg, String img, Object obj, MessageModel.TYPE type);

    void getUser();

    void doInitText2Speech();

    void updateUser(String name, String response);
}

