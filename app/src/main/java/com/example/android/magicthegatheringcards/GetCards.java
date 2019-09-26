package com.example.android.magicthegatheringcards;

import android.util.JsonReader;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import android.os.AsyncTask;

public class GetCards extends AsyncTask<Void, String, Void>{
    private ArrayList<Card> data;
    Card card;
    private String key;

    protected GetCards(ArrayList<Card> namesC){
        data = namesC;
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
            jsonReader.nextName();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    key = jsonReader.nextName();
                    if (key.equals("name")) {
                        card.name = jsonReader.nextString();
                    //} else if (key.equals("manaCost")){
                    //    manaCosts.add(jsonReader.nextString());
                    //} else if (key.equals("type")){
                    //    network += jsonReader.nextString();
                    //} else if (key.equals("text")){
                    //    network += jsonReader.nextString() + "\n" + "\n";
                    } else {
                        jsonReader.skipValue();
                        data.add(card);
                    }
                }
                jsonReader.endObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Card> getData() {
        return data;
    }
}