package com.example.android.magicthegatheringcards;

import android.util.JsonReader;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import android.os.AsyncTask;
import android.util.Log;

public class GetCards extends AsyncTask<Void, String, Void>{
    private ArrayList<Card> data;
    private String key;

    protected GetCards(ArrayList<Card> dataC){
        data = dataC;
    }
    @Override
    protected Void doInBackground(Void... arg0) {
        try {
            URL source = new URL("https://api.magicthegathering.io/v1/cards");
            HttpsURLConnection connection = (HttpsURLConnection) source.openConnection();

            connection.setRequestProperty("Page-Size", "50");
            connection.setRequestProperty("Count", "50");
            connection.setRequestProperty("Total-Count", "31090");

            InputStream responseBody = connection.getInputStream();
            InputStreamReader responseBodyReader =
                    new InputStreamReader(responseBody, "UTF-8");
            JsonReader jsonReader = new JsonReader(responseBodyReader);
            jsonReader.beginObject();
            jsonReader.nextName();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                Card card = new Card();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    key = jsonReader.nextName();
                    if (key.equals("name")) {
                        card.name = jsonReader.nextString();
                    } else if (key.equals("manaCost")) {
                        card.manaCost = jsonReader.nextString();
                    } else if (key.equals("type")) {
                        card.type = jsonReader.nextString();
                    } else if (key.equals("text")) {
                        card.text = jsonReader.nextString();
                    } else if (key.equals("imageUrl")) {
                        card.imageUrl = jsonReader.nextString();
                    } else {
                        jsonReader.skipValue();
                    }
                }
                data.add(card);
                jsonReader.endObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}