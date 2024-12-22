package com.example.lab2;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageButton;

public class Card extends LinearLayout {

    int count = 0;
    int price;
    String category;

    ImageView image;
    TextView nameText;
    TextView countText;
    TextView priceText;

    ImageButton increaseButton;
    ImageButton decreaseButton;


    public Card(Context context, String category, String name, int price, int imageId) {
        super(context);
        this.init(category, name, price, imageId);
    }

    private void init(String category, String name, int price, int imageId) {
        this.price = price;
        this.category = category;

        inflate(getContext(), R.layout.card_layout, this);
        this.image = (ImageView)findViewById(R.id.image);
        this.priceText = (TextView)findViewById(R.id.price);
        this.nameText = (TextView)findViewById(R.id.name);

        this.image.setImageResource(imageId);

        this.priceText.setText(this.price + " ₽");
        this.nameText.setText(name);

        this.countText = (TextView)findViewById(R.id.count);
        this.countText.setText(Integer.toString(this.count));

        this.increaseButton = (ImageButton)findViewById(R.id.increaseButton);
        this.increaseButton.setOnClickListener(view -> this.increaseCount());


        this.decreaseButton = (ImageButton)findViewById(R.id.decreaseButton);
        this.decreaseButton.setOnClickListener(view -> this.decreaseCount());

        this.setOnClickListener(view -> increaseCount());
    }

    public void increaseCount() {
        this.count += 1;
        countText.setText(Integer.toString(this.count));
    }

    public void decreaseCount() {
        this.count = this.count - 1 < 0 ? 0 : this.count - 1;
        countText.setText(Integer.toString(this.count));
    }
}
