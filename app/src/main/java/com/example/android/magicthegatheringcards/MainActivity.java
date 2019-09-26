package com.example.android.magicthegatheringcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Card> data = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.cards);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetCards getCards = new GetCards(data);
        getCards.execute();
        data = getCards.getData();

        recyclerView.setAdapter(new MyAdapter(data));
    }
}