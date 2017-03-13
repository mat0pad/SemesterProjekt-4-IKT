package com.sem4ikt.uni.recipefinderchatbot;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;

/**
 * Created by henriknielsen on 12/03/2017.
 */

public class ToneAnalyzerService {

    private ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);

    public ToneAnalyzerService() {
        service.setUsernameAndPassword("<username>", "<password>");
    }

    public ToneAnalyzerService(String username, String password) {
        service.setUsernameAndPassword(username, password);
    }

    public ToneAnalyzer getTone(String textToAnalyze) {

    }
}
