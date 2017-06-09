package com.example.alprybysh.recipeapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by aprybysh on 6/6/17.
 */

public class ShowIngredientsService extends IntentService {

    public static final String ACTION_FETCH = "com.example.android.recipeaapp.action.water_plants";


    public ShowIngredientsService() {
        super("ShowIngredientsService");
    }


    public static void startActionUndateWidget(Context context) {
        Intent intent = new Intent(context, ShowIngredientsService.class);
        intent.setAction(ACTION_FETCH);
        context.startService(intent);

    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCH.equals(action)) {
                handleAction();
            }
        }
    }


    private void handleAction() {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, AppWidgetProvider.class));
        //Now update all widgets
         RecipeAppWidget.updatePlantWidgets(this, appWidgetManager,  appWidgetIds);

    }
}
