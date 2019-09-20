package com.example.android.magicthegatheringcards;

import android.util.JsonReader;
import java.util.ArrayList;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import android.os.AsyncTask;
import android.widget.TextView;

public class GetCards extends AsyncTask<Void, Void, Void>{
    ArrayList<String> value;
    String key;
    TextView result;
    String network = "";

    protected GetCards(String keyC, ArrayList<String> valueC, TextView resultC){
        key = keyC;
        value = valueC;
        result = resultC;
    }

    @Override
    protected void onProgressUpdate(Void... arg0){
        result.setText(network);
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        try {
            URL source = new URL("https://api.magicthegathering.io/v1/cards");
            HttpsURLConnection connection = (HttpsURLConnection) source.openConnection();
            InputStream responseBody = connection.getInputStream();
            InputStreamReader responseBodyReader =
                    new InputStreamReader(responseBody, "UTF-8");
            JsonReader jsonReader = new JsonReader(responseBodyReader);
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                key = jsonReader.nextName();
                if (key.equals("Sorcery")) {
                    network =
                            result.getText().toString() + value.add(jsonReader.nextString()) + "\n";
                    break;
                } else {
                    jsonReader.skipValue();
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        result.setText(network);
    }
}