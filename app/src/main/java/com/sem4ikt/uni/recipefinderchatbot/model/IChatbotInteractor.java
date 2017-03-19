package com.sem4ikt.uni.recipefinderchatbot.model;

/**
 * Created by henriknielsen on 16/03/2017.
 */

interface IChatbotInteractor {
   ChatbotInteractor.ChatbotCall message(String workspaceId, String msg);
}
