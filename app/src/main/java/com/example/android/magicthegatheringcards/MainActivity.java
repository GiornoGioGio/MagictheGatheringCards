package com.example.android.magicthegatheringcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import java.util.ArrayList;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import android.os.AsyncTask;

public class MainActivity extends AsyncTask<Void, Void, String[]>{
    ArrayList<String> value;
    String key;

    @Override
    protected String[] doInBackground(Void... arg0) {
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
                if (key.equals("organization_url")) {
                    value.add(jsonReader.nextString());
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

}