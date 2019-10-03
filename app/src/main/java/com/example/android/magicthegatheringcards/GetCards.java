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

public class GetCards extends AsyncTask<Void, Integer, Void>{
    private ArrayList<Card> data;
    private String key;
    int pageNumber;

    protected GetCards(ArrayList<Card> dataC, int pageNumberC){
        data = dataC;
        pageNumber = pageNumberC;
    }
    @Override
    protected Void doInBackground(Void... arg0) {
        StringBuilder sb;
        try {
            URL source = new URL("https://api.magicthegathering.io/v1/cards?&page=" + pageNumber);
            HttpsURLConnection connection = (HttpsURLConnection) source.openConnection();

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
                        //sb = new StringBuilder(card.manaCost);
                        /*for (int i=0; i<card.manaCost.length(); i+=2){
                            sb.deleteCharAt(i);
                        }*/
                        /*for (int i=0; !card.manaCost.trim().equals(null); i++) {
                            if (card.manaCost.contains("R")){
                                manaCostL = card.manaCost.length();
                                index = card.manaCost.indexOf('R');
                                properL =manaCostL - index;
                                card.redManaPos.add(properL);
                                sb.replace(i,i," ");
                            }
                            if (card.manaCost.contains("U")){
                                manaCostL = card.manaCost.length();
                                index = card.manaCost.indexOf('U');
                                properL =manaCostL - index + 1;
                                card.blueManaPos.add(properL);
                                sb.replace(i,i," ");
                            }
                            if (card.manaCost.contains("G")){
                                manaCostL = card.manaCost.length();
                                index = card.manaCost.indexOf('G');
                                properL =manaCostL - index + 1;
                                card.greenManaPos.add(properL);
                                sb.replace(i,i," ");
                            }
                            if (card.manaCost.contains("W")){
                                manaCostL = card.manaCost.length();
                                index = card.manaCost.indexOf('W');
                                properL =manaCostL - index + 1;
                                card.whiteManaPos.add(properL);
                                sb.replace(i,i," ");
                            }
                            if (card.manaCost.contains("B")){
                                manaCostL = card.manaCost.length();
                                index = card.manaCost.indexOf('B');
                                properL =manaCostL - index + 1;
                                card.blackManaPos.add(properL);
                                sb.replace(i,i," ");
                            }
                            else {
                                sb.replace(i,i," ");
                            }
                        }*/
                    } else if (key.equals("type")) {
                        card.type = jsonReader.nextString();
                    } else if (key.equals("text")) {
                        card.text = jsonReader.nextString();
                    } else if (key.equals("imageUrl")) {
                        card.imageUrl = jsonReader.nextString();
                    } else if (key.equals("power")) {
                        card.power = jsonReader.nextString();
                    } else if (key.equals("toughness")) {
                        card.toughness = jsonReader.nextString();
                    } else if (key.equals("rarity")) {
                        card.rarity = jsonReader.nextString();
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