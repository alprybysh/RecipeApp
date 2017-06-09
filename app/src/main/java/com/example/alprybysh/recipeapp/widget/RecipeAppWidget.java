package com.example.alprybysh.recipeapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.alprybysh.recipeapp.MainActivity;
import com.example.alprybysh.recipeapp.R;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = "Privet";
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
//        Intent newIntent = new Intent(context, MainActivity.class);
//        PendingIntent wPendingInent  = PendingIntent.getActivity(context, 0, newIntent, 0);
//        views.setOnClickPendingIntent(R.id.appwidget_text, wPendingInent);

        Intent updateIntent = new Intent(context, ShowIngredientsService.class);
        updateIntent.setAction(ShowIngredientsService.ACTION_FETCH);
        PendingIntent updatePendingIntent = PendingIntent.getService(context, 0, updateIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.appwidget_text, updatePendingIntent);



        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }




    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them


        ShowIngredientsService.startActionUndateWidget(context);
    }


    public static void updatePlantWidgets (Context context, AppWidgetManager appWidgetManager,
                                           int[] appWidgetIds){

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }


    }



    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

