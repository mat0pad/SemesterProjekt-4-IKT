package com.sem4ikt.uni.recipefinderchatbot;



import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.RequestBuilder;
import com.ibm.watson.developer_cloud.http.ServiceCall;

import java.util.Map;


/**
 * Created by henriknielsen on 11/03/2017.
 */

public class ConversationService {
    private com.ibm.watson.developer_cloud.conversation.v1.ConversationService service = new com.ibm.watson.developer_cloud.conversation.v1.ConversationService(com.ibm.watson.developer_cloud.conversation.v1.ConversationService.VERSION_DATE_2016_07_11);

    private Map<String, Object> context;

    public ConversationService(){
        service.setUsernameAndPassword("f6c68c53-70a5-4a8c-af70-41a5eed85690", "1pMBh1PJOxP0");
    }

    public ConversationService(String username, String password){
        service.setUsernameAndPassword(username, password);
    }

    protected void onCreate(){

        // Test begin
        // Causes exception if run on the GUI/main thread because this is a (blocking) network request
        // Create separate thread
        /*Runnable runnable = new Runnable()
        {
            public void run()
            {

            }
        };
        Thread myThread = new Thread(runnable);
        myThread.start();*/
        // Test end
    }

    public MessageResponse message(String workspaceID, String msg){
        MessageRequest newMessage = new MessageRequest.Builder().context(context).inputText(msg).build();
        MessageResponse response = service.message(workspaceID ,newMessage).execute();
        context = response.getContext();
        return response;
    }
}
