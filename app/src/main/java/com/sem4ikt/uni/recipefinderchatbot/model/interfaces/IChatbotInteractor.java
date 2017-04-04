package com.sem4ikt.uni.recipefinderchatbot.model.interfaces;

import com.sem4ikt.uni.recipefinderchatbot.model.ChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;

/**
 * Created by henriknielsen on 16/03/2017.
 */

public interface IChatbotInteractor {
   ChatbotInteractor.ChatbotCall message(String workspaceId, String msg);

   void setContext(User user);
}
