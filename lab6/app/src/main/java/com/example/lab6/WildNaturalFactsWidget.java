package com.example.lab6;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Implementation of App Widget functionality.
 */
public class WildNaturalFactsWidget extends AppWidgetProvider {
    static boolean isMyText = false;
    static final Random rng = new Random();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        SharedPreferences sp = context.getSharedPreferences("widget_config", MODE_PRIVATE);
        for (int appWidgetId : appWidgetIds) {
            setupRemoteViews(context, appWidgetManager, sp,  appWidgetId);
        }

        isMyText = !isMyText;
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static int getRandomNumber(int min, int max) {
        return rng.nextInt((max - min) + 1) + min;
    }

    public static void setupRemoteViews(Context constext, AppWidgetManager manager, SharedPreferences sp, int widgetId) {
        RemoteViews views = new RemoteViews(constext.getPackageName(), R.layout.wild_natural_facts_widget);
        List<Integer> availableFacts = new ArrayList<>();
        for (int i =0; i < 10; i++) {
            if (sp.getBoolean("fact" + (i + 1), false)) {
                availableFacts.add(i);
            }
        }
        int drawableId = 0, stringId = 0;

        int i = getRandomNumber(0, 9);
        while (!availableFacts.contains(i)) {
            i = getRandomNumber(0, 9);
        }
        switch (i)  {
            case 0:
                stringId = R.string.fact01;
                drawableId = R.drawable.fact01;
                break;
            case 1:
                stringId = R.string.fact02;
                drawableId = R.drawable.fact02;
                break;
            case 2:
                stringId = R.string.fact03;
                drawableId = R.drawable.fact03;
                break;
            case 3:
                stringId = R.string.fact04;
                drawableId = R.drawable.fact04;
                break;
            case 4:
                stringId = R.string.fact05;
                drawableId = R.drawable.fact05;
                break;
            case 5:
                stringId = R.string.fact06;
                drawableId = R.drawable.fact06;
                break;
            case 6:
                stringId = R.string.fact07;
                drawableId = R.drawable.fact07;
                break;
            case 7:
                stringId = R.string.fact08;
                drawableId = R.drawable.fact08;
                break;
            case 8:
                stringId = R.string.fact09;
                drawableId = R.drawable.fact09;
                break;
            case 9:
                stringId = R.string.fact10;
                drawableId = R.drawable.fact10;
                break;
        }

        views.setTextViewText(R.id.Text, constext.getString(stringId));
        views.setImageViewResource(R.id.Image, drawableId);

        manager.updateAppWidget(widgetId, views);
    }
}