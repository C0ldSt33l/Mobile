package com.example.lab2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private LinearLayout scrollBarBody;
    private ImageButton filterBtn;
    private Card[] cards;

    private ArrayList<String> applyedFilters = new ArrayList<>();
    private final String[] allFilters = { "мебель", "бытовая техника", "еда", "инструменты", "автомобильные" };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.scrollBarBody = (LinearLayout)findViewById(R.id.CardHost);
        this.filterBtn = (ImageButton)findViewById(R.id.FilterButton);
        this.filterBtn.setOnClickListener(view -> this.createFilterMenu());

        this.cards = new Card[] {
                new Card(this, "мебель", "стул", 50),
                new Card(this, "мебель", "диван", 30),

                new Card(this, "бытовая техника", "стиральная машина", 30),
                new Card(this, "бытовая техника", "холодильник", 30),

                new Card(this, "еда", "бургер", 30),
                new Card(this, "еда", "салат", 30),

                new Card(this, "инструменты", "отвёртка", 30),
                new Card(this, "инструменты", "молоток", 30),

                new Card(this, "автомобильные", "шины", 30),
                new Card(this, "автомобильные", "диски", 30),
        };
        for (var card: cards) {
            this.scrollBarBody.addView(card);
        }

//        this.cards[0].setVisibility(View.GONE);
    }

    public void calculatePrice(View view) {
        var prices = new HashMap<String, Integer>();
        Log.d("MAP SIZE", Integer.toString((prices.size())));
        for (var card: this.cards) {
            if (card.count == 0) continue;
            prices.put(card.name.getText().toString(), card.price * card.count);
        }

        if (prices.size() == 0) {
            Toast.makeText(this, "Ни один из товаров не выбран", Toast.LENGTH_SHORT).show();
        }
        else {
            var intent = new Intent(this, Bill.class);
            intent.putExtra("prices", prices);
            startActivity(intent);
        }
    }

    private void createFilterMenu() {
        var builder = new AlertDialog.Builder(this);
        builder.setTitle("Категории");
        builder.setCancelable(false);

        var checkers = new boolean[this.allFilters.length];
        for (var i = 0; i < checkers.length; i++) {
            checkers[i] = this.applyedFilters.contains(this.allFilters[i]);
        }

        builder.setMultiChoiceItems(this.allFilters, checkers, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                var allFilters = MainActivity.this.allFilters;
                var applyedFilters = MainActivity.this.applyedFilters;
                if (isChecked) applyedFilters.add(allFilters[which].toLowerCase());
                else applyedFilters.remove(allFilters[which].toLowerCase());
            }
        });
        builder.setPositiveButton("Принять", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                var cards = MainActivity.this.cards;
                var applyedFilters = MainActivity.this.applyedFilters;

                for (var card : cards) {
                    if (!applyedFilters.contains(card.category.toLowerCase())) {
                        card.setVisibility(View.GONE);
                    }
                }
            }
        });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNeutralButton("Отчистить всё", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.applyedFilters.clear();
            }
        });

        builder.show();
   }
}