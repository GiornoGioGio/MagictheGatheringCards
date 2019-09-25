package com.example.android.magicthegatheringcards;

import android.util.JsonReader;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import android.os.AsyncTask;
import android.widget.TextView;

public class GetCards extends AsyncTask<Void, String, Void>{
    ArrayList<String> value;
    String key;
    TextView result;
    String network = "";

    protected GetCards(ArrayList<String> valueC, TextView resultC){
        value = valueC;
        result = resultC;
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
                        network += jsonReader.nextString() + "      ";
                    } else if (key.equals("manaCost")){
                        network += jsonReader.nextString() + "\n";
                    } else if (key.equals("type")){
                        network += jsonReader.nextString() + "\n";
                    } else if (key.equals("text")){
                        network += jsonReader.nextString() + "\n" + "\n";
                    } else {
                        jsonReader.skipValue();
                        publishProgress(network);
                    }
                }
                jsonReader.endObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... arg0){
        result.setText(network);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        result.setText(network);
    }
}