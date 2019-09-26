package com.example.android.magicthegatheringcards;

public class Card {
    String name;
    String manaCost;
    String imageUrl;
    String type;
    String text;

    public Card(String nameC, String manaCostC, String imageUrlC, String typeC, String textC) {
        name = nameC;
        manaCost = manaCostC;
        imageUrl = imageUrlC;
        type = typeC;
        text = textC;
    }
}
