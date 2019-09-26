package com.example.android.magicthegatheringcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Card> data = new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter myAdapter = new MyAdapter(data);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.cards);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetCards getCards = new GetCards(data,this);
        getCards.execute();

        data = getCards.getData();

    }

    void mainSetAdapter() {
        recyclerView.setAdapter(myAdapter);
    }
}