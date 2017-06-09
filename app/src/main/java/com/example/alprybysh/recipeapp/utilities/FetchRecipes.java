package com.example.alprybysh.recipeapp.utilities;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.alprybysh.recipeapp.R;
import com.example.alprybysh.recipeapp.Recipe;

import java.util.ArrayList;

/**
 * Created by aprybysh on 5/14/17.
 */

public class FetchRecipes extends AsyncTask<Void, Void, ArrayList<Recipe>> {

    private ProgressBar progressBar;
    private Context context;
    private JsonReaderHelper jsonReaderHelper;
    private CardViewAdapter adapter;


    public FetchRecipes(Context context, CardViewAdapter adapter) {

        this.context = context;
        progressBar = (ProgressBar) ((Activity) context).findViewById(R.id.pb_loading_indicator);
        this.adapter = adapter;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    protected ArrayList<Recipe> doInBackground(Void... params) {

        jsonReaderHelper = new JsonReaderHelper();
        return jsonReaderHelper.getRecipes(context);

    }

    @Override
    protected void onPostExecute(ArrayList<Recipe> recipes) {
        super.onPostExecute(recipes);
        progressBar.setVisibility(View.INVISIBLE);

        if (adapter != null){
            adapter.setCardAdapterDate(recipes);
        }


    }
}
