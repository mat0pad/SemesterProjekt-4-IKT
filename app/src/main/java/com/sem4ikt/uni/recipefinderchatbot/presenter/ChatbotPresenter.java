package com.sem4ikt.uni.recipefinderchatbot.presenter;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.VisibleForTesting;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ChatListAdapter;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackMealPlanAdd;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.ICallbackUser;
import com.sem4ikt.uni.recipefinderchatbot.database.Interface.IFirebaseDBInteractors;
import com.sem4ikt.uni.recipefinderchatbot.database.MealPlansInteractor;
import com.sem4ikt.uni.recipefinderchatbot.database.UserInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.ChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.ConversationInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.MoreRecipeMessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.SingleRecipeMessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.firebasedb.User;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IChatbotInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.IConversationInteractor;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanWeekModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatbotPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatbotView;

import java.util.Date;

/**
 * Created by mathiaslykkepedersen on 09/03/2017.
 */

public class ChatbotPresenter extends BasePresenter<IChatbotView> implements IChatbotPresenter<IChatbotView>,ICallbackUser,ICallbackMealPlanAdd {

    private static String[] nodesVisited = new String[2];
    private boolean isInGeneral = true;

    private IConversationInteractor api;
    private IChatbotInteractor ci;
    private IFirebaseDBInteractors.IUserInteractor ui;
    private IFirebaseDBInteractors.IMealplanInteractor mi;
    private Handler mainHandler;



    public ChatbotPresenter(IChatbotView view, IConversationInteractor conv, IFirebaseDBInteractors.IUserInteractor user,
                            IChatbotInteractor chatbot, IFirebaseDBInteractors.IMealplanInteractor mpi,Handler handler)
    {
        super(view);
        api = conv;
        api.setPresenter(this);
        ui = user;
        ci = chatbot;
        mi = mpi;
        mainHandler = handler;

    }

    @Override
    public void send(String input) {

        // Disable send
        view.shouldSendButton(false);

        view.displayNormalMessage(new MessageModel(input, ChatListAdapter.DIRECTION_OUTGOING, MessageModel.TYPE.NORMAL));

        doMessage((isInGeneral ? "e665abad-a305-4cf4-a21c-045354782015" : "49630f5e-f2b9-453a-be68-927f17cf64bc"), input);
    }

    @Override
    public void switchWorkspace(int spaceId, String lastInput) {

        // 0 = Generel, 1 = rR
        doMessage((spaceId == 0 ? "e665abad-a305-4cf4-a21c-045354782015" : "49630f5e-f2b9-453a-be68-927f17cf64bc"), lastInput);

    }

    // Used by send & switchWorkspace
    private void doMessage(String workspaceId, String input) {

        ci.message(workspaceId, input).setChatbotListener(new ChatbotInteractor.ChatbotListener() {
            @Override
            public void onChatbotResponse(final MessageResponse response) {
                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        if (response.getOutput().containsKey("action")) {
                            resetNodesVisited();
                            System.out.println("Action found!");
                            api.performAction(response.getOutput().get("action").toString(), response);
                        } else if (response.getOutput().containsKey("goTo")) {

                            isInGeneral = !isInGeneral;
                            System.out.println(response.toString());

                            if (isLooping(response.getOutput().get("nodes_visited").toString()))
                                handleLooping();

                            else
                                api.switchWorkspace(response.getOutput().get("goTo")
                                        .toString(), response.getInputText());

                        } else {
                            resetNodesVisited();
                            System.out.println(response);
                            showText(response.getText().toString()
                                    .substring(1, response.getText().toString().length() - 1));
                        }
                    }
                };
                mainHandler.post(myRunnable);
            }

            @Override
            public void onChatbotFailed(String errorMsg) {
                System.out.println(errorMsg + "thread: " + Thread.currentThread());

                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        showErrorText();
                    }
                };
                new Handler(Looper.getMainLooper()).post(myRunnable);
            }
        });

    }

    @Override
    public void showErrorText() {

        // Play
        view.play("An error occurred. Please try again.");

        view.displayNormalMessage(new MessageModel("An error occurred. Please try again.", ChatListAdapter.DIRECTION_INCOMING, MessageModel.TYPE.NORMAL));

        // Enable send
        view.shouldSendButton(true);
    }

    @Override
    public void showText(String msg) {

        if (msg != null) {
            // Play
            view.play(msg);

            view.displayNormalMessage(new MessageModel(msg, ChatListAdapter.DIRECTION_INCOMING, MessageModel.TYPE.NORMAL));
        } else
            showErrorText();

        // Enable send
        view.shouldSendButton(true);
    }

    @Override
    public void showSingleRecipeText(String msg, String img, int id) {

        if (msg != null) {
            // Play
            view.play(msg);

            view.displayNormalMessage(new SingleRecipeMessageModel(msg, ChatListAdapter.DIRECTION_INCOMING, img, id));
        } else
            showErrorText();

        // Enable send
        view.shouldSendButton(true);
    }

    @Override
    public void showMoreRecipesText(String msg, String img, Object obj, MessageModel.TYPE type) {

        if (msg != null) {
            // Play
            view.play(msg);

            view.displayNormalMessage(new MoreRecipeMessageModel(msg, ChatListAdapter.DIRECTION_INCOMING, img, obj, type));
        } else
            showErrorText();

        // Enable send
        view.shouldSendButton(true);
    }

    @Override
    public void getUser() {
        ui.getUser(this);
    }

    @Override
    public void doInitText2Speech() {
        view.initText2Speech();
    }

    @Override
    public void updateUser(String name, String response) {
        ui.updateUser(name, true);
        showText(response);
    }

    @Override
    public void addMealPlanWeek(MealPlanWeekModel model, Date date) {
        mi.addMealPlanWeek(model, date,this);
    }

    @Override
    public void addMealPlanDay(MealPlanDayModel model, Date date) {
        mi.addMealPlanDay(model, date,this);
    }

    @Override
    public void onReceived(User user, UserCallbackType type) {

        switch (type) {
            case USER_FOUND:
                ci.setContext(user);
                break;
            case USER_NOT_FOUND:
                ci.setContext(null);
                ui.addUser(new User());
                break;
            default:
                break;
        }

        switchWorkspace(0, " ");
    }

    @Override
    public void onFailure() {
        ci.setContext(null);
        switchWorkspace(0," ");
    }

    @Override
    public void onReceivedMealPlanAdd() {
        showText("The meal plan has been successfully saved, you can now inspect it in the meal plan tab");
    }

    @Override
    public void onFailureMealPlanAdd() {
        showText("Error! There seems to already be a meal plan saved in this time interval");
    }

    // Below functions are to prevent loop in workspaces
    private boolean isLooping(String nodes_visited) {

        JsonArray entries = (JsonArray) new JsonParser().parse(nodes_visited);

        if (entries.size() != 0) {

            if (entries.get(entries.size() - 1).getAsString().equals("Search"))
                nodesVisited[0] = entries.get(entries.size() - 1).getAsString();
            else
                nodesVisited[1] = entries.get(entries.size() - 1).getAsString();
        }

        // nodesVisited[0].equals("Search") && nodesVisited[1].equals("JumpBack")
        return !nodesVisited[0].isEmpty() && !nodesVisited[1].isEmpty();
    }

    private void resetNodesVisited() {
        nodesVisited[0] = "";
        nodesVisited[1] = "";
    }

    private void handleLooping() {

        ci.message("e665abad-a305-4cf4-a21c-045354782015", "anything_else")
                .setChatbotListener(new ChatbotInteractor.ChatbotListener() {
                    @Override
                    public void onChatbotResponse(final MessageResponse response) {
                        Handler mainHandler = new Handler(Looper.getMainLooper());

                        Runnable myRunnable = new Runnable() {
                            @Override
                            public void run() {

                                isInGeneral = true;

                                resetNodesVisited();

                                showText(response.getText().toString()
                                        .substring(1, response.getText().toString().length() - 1));
                            }
                        };
                        mainHandler.post(myRunnable);
                    }

                    @Override
                    public void onChatbotFailed(String errorMsg) {
                        System.out.println(errorMsg);
                        showErrorText();
                    }
                });

    }
}