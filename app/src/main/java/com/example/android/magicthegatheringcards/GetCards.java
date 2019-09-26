package com.example.android.magicthegatheringcards;

import android.util.JsonReader;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import android.os.AsyncTask;

public class GetCards extends AsyncTask<ArrayList<Card>, String, ArrayList<Card>> {
    private ArrayList<Card> data;
    private WeakReference<MyTaskInformer> callBack;
    Card card = new Card();
    private String key;

    public GetCards(ArrayList<Card> dataC, MyTaskInformer callbackC){
        data = dataC;
        this.callBack = new WeakReference<>(callbackC);
    }
    @Override
    protected ArrayList<Card> doInBackground(ArrayList<Card>... arg0) {
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
                    } else if (key.equals("manaCost")){
                        card.manaCost = jsonReader.nextString();
                    //} else if (key.equals("type")){
                    //    card.type = jsonReader.nextString();
                    //} else if (key.equals("text")){
                    //    card.text = jsonReader.nextString();
                    //}else if (key.equals("imageUrl")){
                    //    card.imageUrl = jsonReader.nextString();
                    } else {
                        jsonReader.skipValue();
                        data.add(card);
                        publishProgress();
                        card.clear();
                    }
                }
                jsonReader.endObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<Card> c) {
        super.onPostExecute(c);
        final MyTaskInformer mCallBack = callBack.get();

        if(callBack != null) {
            mCallBack.onTaskDone(c);
        }
    }

}