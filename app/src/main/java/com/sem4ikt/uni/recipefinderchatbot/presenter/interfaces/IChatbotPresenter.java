package com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;

/**
 * Created by mathiaslykkepedersen on 09/03/2017.
 */

public interface IChatbotPresenter<V> extends IBasePresenter<V> {

    void send(String input);
    void switchWorkspace(int spaceId, String lastInput);
    void showText(String text);

    void getUser();
    void callFromDatabase(User user);

    void showSingleRecipeText(String msg, String img, int id);

}

