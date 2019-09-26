package com.example.android.magicthegatheringcards;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {
    private ArrayList<Card> data;

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mMana;
        public TextView mType;
        public TextView mText;

        public MyViewHolder(View pItem) {
            super(pItem);
            mName = pItem.findViewById(R.id.card_name);
            mMana = pItem.findViewById(R.id.card_mana_cost);
            mType = pItem.findViewById(R.id.card_type);
            mText = pItem.findViewById(R.id.card_text);
        }
    }

    public MyAdapter (ArrayList<Card> mData) {
        data = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        ((MyViewHolder) viewHolder).mName.setText(data.get(i).name);
        ((MyViewHolder) viewHolder).mMana.setText(data.get(i).manaCost);
        ((MyViewHolder) viewHolder).mType.setText(data.get(i).type);
        ((MyViewHolder) viewHolder).mText.setText(data.get(i).text);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
