package com.sem4ikt.uni.recipefinderchatbot.services;

import android.os.AsyncTask;

import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

/**
 * Created by mathiaslykkepedersen on 15/04/2017.
 */

public class Text2Speech {

    private TextToSpeech textToSpeech = null;
    private StreamPlayer player = null;

    public Text2Speech() {

        // Init text 2 speech
        textToSpeech = new TextToSpeech();
        textToSpeech.setUsernameAndPassword("3a77d57f-757f-42ed-a808-279853d76354", "Cox4e6zX4JXD");

        // Init player
        player = new StreamPlayer();
    }

    public void playerStart(String textStream) {

        if (textStream != null)
            new SynthesisTask().execute(textStream);

    }

    public void playerStop() {
        player.interrupt();
    }


    private class SynthesisTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            player.playStream(textToSpeech.synthesize(params[0], Voice.EN_MICHAEL).execute());

            return "Did synthesize";
        }
    }

}
