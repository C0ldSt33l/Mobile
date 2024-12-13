package com.example.lab2;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageButton;

public class Card extends LinearLayout {
    int count = 0;
    int price;
    String category;

    ImageView image;
    TextView name;
    TextView countText;

    ImageButton increaseButton;
    ImageButton descreaseButton;


    public Card(Context context, String category, String name, int price) {
        super(context);
        this.init(category, name, price);
    }

    private void init(String category, String name, int price) {
        this.price = price;
        this.category = category;

        inflate(getContext(), R.layout.card_layout, this);
        this.image = (ImageView)findViewById(R.id.image);
        this.name = (TextView)findViewById(R.id.name);
        this.name.setText(name);

        this.countText = (TextView)findViewById(R.id.count);
        this.countText.setText(Integer.toString(this.count));

        this.increaseButton = (ImageButton)findViewById(R.id.increaseButton);
        this.increaseButton.setOnClickListener(view -> {
            this.count += 1;
            countText.setText(Integer.toString(this.count));
        });

        this.descreaseButton = (ImageButton)findViewById(R.id.decreaseButton);
        this.descreaseButton.setOnClickListener(view -> {
            this.count = this.count - 1 < 0 ? 0 : this.count - 1;
            countText.setText(Integer.toString(this.count));
        });
    }
}
