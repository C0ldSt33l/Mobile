package com.example.lab1;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                }
        );

    }

    public void FindMoreCommonDigitInNumber(View view) {
        EditText number_view = findViewById(R.id.NumberField);
        TextView result = findViewById(R.id.Result);
//        Integer number = Integer.parseInt(number_view.getText().toString());
        String number_str = number_view.getText().toString();

//        number_view.setText(number.toString());

//        Log.d("NUMBER", number.toString());

        if (number_str.isEmpty()) {
            Toast.makeText(MainActivity.this, "Введите число", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d("GETTED NUMBER", number_str);
            number_str = (new BigInteger(number_str)).toString();
            number_view.setText(number_str);

            Log.d("BIGINTEGER", (new BigInteger(number_str)).toString());

            Map<Character, Integer> digit_map = new HashMap();
            for (char ch : number_str.toCharArray()) {
                digit_map.put(ch, digit_map.containsKey(ch) ? digit_map.get(ch) + 1 : 1);
            }

            String common_digit = Collections.max(digit_map.entrySet(), Map.Entry.comparingByValue()).getKey().toString();
            result.setText(common_digit);
            Log.d("COMMON DIGIT", common_digit);

            Log.d("MAPPA", digit_map.toString());
        }
    }
}