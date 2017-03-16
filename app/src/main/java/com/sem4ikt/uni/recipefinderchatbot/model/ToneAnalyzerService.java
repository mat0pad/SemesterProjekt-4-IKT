package com.sem4ikt.uni.recipefinderchatbot.model;

import com.ibm.watson.developer_cloud.http.ServiceCallback;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;

/**
 * Created by henriknielsen on 12/03/2017.
 */


// Klassens funktionalitet er flyttet til Conversationservice da de to har en tæt kobling.

/*
public class ToneAnalyzerService {

    private ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);

    public ToneAnalyzerService() {
        service.setUsernameAndPassword("<username>", "<password>");
    }

    public ToneAnalyzerService(String username, String password) {
        service.setUsernameAndPassword(username, password);
    }

    public ToneAnalyzer getTone(String textToAnalyze) {
        service.getTone(textToAnalyze, null).enqueue(new ServiceCallback<ToneAnalysis>() {
            @Override
            public void onResponse(ToneAnalysis response) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
*/