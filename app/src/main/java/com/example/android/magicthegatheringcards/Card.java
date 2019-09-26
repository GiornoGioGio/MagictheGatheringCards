package com.example.android.magicthegatheringcards;

public class Card {
    String name;
    String manaCost;
    String imageUrl;
    String type;
    String text;

    public void clear() {
        name = null;
        manaCost = null;
        imageUrl = null;
        type = null;
        text = null;
    }
}
