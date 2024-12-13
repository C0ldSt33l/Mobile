package com.example.lab2;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class Bill extends AppCompatActivity {
    LinearLayout priceContainer;
    TextView summa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bill);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.priceContainer = (LinearLayout)findViewById(R.id.PriceContainer);
        this.summa = (TextView)findViewById(R.id.Summa);

        var args = getIntent().getExtras();
        if (args != null) {
            var prices = (HashMap<String, Integer>)args.getSerializable("prices");
            Integer sum = 0;
            for (var pair: prices.entrySet()) {
                sum += pair.getValue();
                this.addPriceRow(pair.getKey() + pair.getValue(), pair.getValue());
            }

            this.summa.setText((String)this.summa.getText() + sum);
        }
    }

    private void addPriceRow(String product, Integer price) {
        var textView = new TextView(this);
        textView.setText(product);
        textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        this.priceContainer.addView(textView);
    }
}