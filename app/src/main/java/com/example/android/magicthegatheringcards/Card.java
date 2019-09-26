package com.example.android.magicthegatheringcards;

public class Card {
    String name;
    String manaCost;
    String imageUrl;
    String type;
    String subtype;
    String text;

    public Card(String nameC, String manaCostC, String imageUrlC, String typeC, String subtypeC, String textC) {
        name = nameC;
        manaCost = manaCostC;
        imageUrl = imageUrlC;
        type = typeC;
        subtype = subtypeC;
        text = textC;
    }
}
