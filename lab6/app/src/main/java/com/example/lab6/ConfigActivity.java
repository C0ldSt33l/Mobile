package com.example.lab6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.appwidget.AppWidgetManager;
import android.view.View;
import android.widget.CheckBox;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class ConfigActivity extends AppCompatActivity {
    int appwidgetId;
    Intent resultValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        appwidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
        if (extras != null) {
            appwidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        if (appwidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appwidgetId);

        // отрицательный ответ
        setResult(RESULT_CANCELED, resultValue);

//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_config);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    public void Accept(View view) {
        CheckBox[] checks = new CheckBox[] {
                findViewById(R.id.Fact1),
                findViewById(R.id.Fact2),
                findViewById(R.id.Fact1),
                findViewById(R.id.Fact4),
                findViewById(R.id.Fact5),
                findViewById(R.id.Fact6),
                findViewById(R.id.Fact7),
                findViewById(R.id.Fact8),
                findViewById(R.id.Fact9),
                findViewById(R.id.Fact10),
        };

        SharedPreferences sp = getSharedPreferences("widget_config", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        for (int i = 0; i < checks.length; i++) {
            editor.putBoolean("fact" + (i + 1), checks[i].isChecked());
        }
        editor.commit();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        WildNaturalFactsWidget.setupRemoteViews(this, appWidgetManager, sp, appwidgetId);

        setResult(RESULT_OK, this.resultValue);
        finish();
    }
}