package com.example.alprybysh.recipeapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;


import com.example.alprybysh.recipeapp.Recipe;
import com.example.alprybysh.recipeapp.data.RecipeData;
import com.example.alprybysh.recipeapp.utilities.JsonReaderHelper;

/**
 * Created by aprybysh on 6/6/17.
 */

public class ShowIngredientsService extends IntentService {

    public static final String ACTION_FETCH = "com.example.android.recipeaapp.action.update_widget";
    public static final String INGR = " ingredients:  ";
    private Recipe recipe;
    private int recipeID;

    JsonReaderHelper jsonReaderHelper;


    public ShowIngredientsService() {
        super("ShowIngredientsService");
    }

    //This method is used to start the Action
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

    // This method is used to handle the Action
    private void handleAction() {

        String widgetInfo;
        recipe = new Recipe();
        jsonReaderHelper = new JsonReaderHelper();
        //Get recipe description form the Json object
        jsonReaderHelper.getRecipes(this).size();
        recipeID = RecipeData.getStRecipeID();
        recipe = jsonReaderHelper.getRecipeDetails(recipeID, this);
        if (recipeID == jsonReaderHelper.getRecipes(this).size() - 1) {
            recipeID = 0;
            RecipeData.setStRecipeID(recipeID);
        } else {
            recipeID++;
            RecipeData.setStRecipeID(recipeID);
        }

        //Build the recipe desciption
        String str = " ";
        StringBuilder builder = new StringBuilder();
        builder.append(jsonReaderHelper.getRecipes(this).get(recipeID).getRecipeName());
        builder.append(INGR);
        for (int i = 0; i < recipe.getIngredient().size(); i++) {
            builder.append(recipe.getQuantity().get(i));
            builder.append(str);
            builder.append(recipe.getMeasure().get(i));
            builder.append(str);
            builder.append(recipe.getIngredient().get(i));
            builder.append("." + str);

        }

        widgetInfo = builder.toString();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeAppWidget.class));
        //Now update all widgets
        RecipeAppWidget.updatePlantWidgets(this, appWidgetManager, widgetInfo, appWidgetIds);

    }
}
