package com.example.android.magicthegatheringcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Card> data = new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter myAdapter = new MyAdapter(data);
    int page = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button next = findViewById(R.id.next);
        Button previous = findViewById(R.id.previous);
        Button last = findViewById(R.id.last_page);

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                page++;
            }
        });

        previous.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (page != 0 || page !=1){ page --; }
            }
        });
        TextView textView = findViewById(R.id.textView);
        textView.setText(textView.getText() + Integer.toString(page));

        recyclerView = findViewById(R.id.cards);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetCards getCards = new GetCards(data, page){

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                recyclerView.setAdapter(myAdapter);
            }
        };
        getCards.execute();
    }
}