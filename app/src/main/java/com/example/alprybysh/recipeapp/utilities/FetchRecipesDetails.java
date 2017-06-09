package com.example.alprybysh.recipeapp.utilities;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.alprybysh.recipeapp.R;
import com.example.alprybysh.recipeapp.Recipe;
import com.example.alprybysh.recipeapp.RecipeDetails;

/**
 * Created by aprybysh on 5/14/17.
 */

public class FetchRecipesDetails extends AsyncTask<Integer, Void, Recipe> {

    Context context;
    ProgressBar progressBar;

    private JsonReaderHelper jsonHelper;


    public FetchRecipesDetails(Context context) {

        this.context = context;
        progressBar = (ProgressBar) ((Activity) context).findViewById(R.id.fragment_loading_indicator);

    }


    @Override
    protected Recipe doInBackground(Integer... params) {

        int id = params[0];

        jsonHelper = new JsonReaderHelper();
        return jsonHelper.getRecipeDetails(id, context);


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onPostExecute(Recipe recipe) {
        progressBar.setVisibility(View.INVISIBLE);
        // adapter.setDataSteps(recipe);

    }


}
