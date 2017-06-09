package com.example.alprybysh.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;


import com.example.alprybysh.recipeapp.utilities.CardViewAdapter;
import com.example.alprybysh.recipeapp.utilities.FetchRecipes;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity implements CardViewAdapter.RecipeOnClickListenerHandler {

    private RecyclerView mRecyclerView;
    private CardViewAdapter adapter;
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        Timber.plant(new Timber.DebugTree());

        /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        boolean tabletSize = getResources().getBoolean(R.bool.has_two_panes);
        if (tabletSize) {
            GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns());
            mRecyclerView.setLayoutManager(layoutManager);
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);
        }

        /*Setting the LinearLayoutManager as a manager for the RecyclerView*/

        adapter = new CardViewAdapter(this, this);

         /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mRecyclerView.setAdapter(adapter);

        FetchRecipes fetchRecipes = new FetchRecipes(this, adapter);

        fetchRecipes.execute();


    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // You can change this divider to adjust the size of the poster
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

    @Override
    public void onItemClick(Recipe itemClicked) {

        Context context = this;
        Class destinationClass = RecipeDetails.class;
        Intent intent = new Intent(context, destinationClass);
        intent.putExtra("RecipeObject", itemClicked);
        startActivity(intent);

    }
}
