package com.example.alprybysh.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.example.alprybysh.recipeapp.data.RecipeData;

import com.example.alprybysh.recipeapp.fragments.DescriptionFragment;
import com.example.alprybysh.recipeapp.fragments.StepsFragment;
import com.example.alprybysh.recipeapp.utilities.FetchRecipesDetails;
import com.example.alprybysh.recipeapp.utilities.JsonReaderHelper;

import java.util.concurrent.ExecutionException;

/**
 * Created by aprybysh on 5/14/17.
 */

public class RecipeDetails extends AppCompatActivity implements StepsFragment.OnRecipeSelectedListener {


    private int mRecipeId;
    private JsonReaderHelper jsonHelper;
    private FetchRecipesDetails recipesDetails;
    public Recipe mRecipe;
    private FragmentManager fragmentManager;
    private int fragmentId;
    private int stepsId;
    private boolean mTwoPane;
    private static final String KEYID = "keyId";
    private static final String STEPID = "stepsID";
    private static final String FRAGMENTID = "fragmentId";
    private static final String TWOPANELKEY = "twoPanalKey";
    private static final int BASEVIDEO = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe_details_layout);
        Intent intentStarted = getIntent();

        Recipe myParcelableObject = intentStarted.getParcelableExtra("RecipeObject");

        //This method is used to find out what device type is using
        if (findViewById(R.id.descroption_container) == null) {

            mTwoPane = false;

        } else {
            mTwoPane = true;
        }

        if (savedInstanceState == null) {

            if (myParcelableObject != null) {
                jsonHelper = new JsonReaderHelper();
                mRecipeId = myParcelableObject.getRecipeID();
                setTitle(myParcelableObject.getRecipeName());
                recipesDetails = new FetchRecipesDetails(this);
                mRecipe = new Recipe();
                 // Set up Recipe data
                try {
                    mRecipe = recipesDetails.execute(mRecipeId - 1).get();
                    RecipeData.setStShortDescription(mRecipe.getShortDescription());
                    RecipeData.setStIngredient(mRecipe.getIngredient());
                    RecipeData.setStDescripton(mRecipe.getDescripton());
                    RecipeData.setStMeasure(mRecipe.getMeasure());
                    RecipeData.setStQuantity(mRecipe.getQuantity());
                    RecipeData.setStVideoURL(mRecipe.getVideoURL());
                    RecipeData.setStStepsID(mRecipe.getStepsID());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();

                }

                if (mTwoPane) {
                    setStepsFragment();
                    fragmentId = 1;
                    onRecipeSelected(BASEVIDEO);
                } else {
                    setStepsFragment();
                    fragmentId = 1;

                }

            }

        } else {
            fragmentId = savedInstanceState.getInt(FRAGMENTID);
            stepsId = savedInstanceState.getInt(STEPID);

            switch (fragmentId) {
                case 1:
                    setStepsFragment();

                    break;

                case 2:
                    setDescriptionFragment();
                    break;
            }

        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(FRAGMENTID, fragmentId);
        outState.putInt(STEPID, stepsId);
    }


    @Override
    public void onBackPressed() {
        fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
            setStepsFragment();
            fragmentId = 1;

        } else {
            super.onBackPressed();
        }
    }
    //This method is used to open video for the selected video
    @Override
    public void onRecipeSelected(int id) {


        if (mTwoPane) {

            stepsId = id;
            Bundle bundle = new Bundle();
            bundle.putInt(KEYID, stepsId);
            bundle.putBoolean(TWOPANELKEY, mTwoPane);
            DescriptionFragment descriptionFragment = new DescriptionFragment();
            descriptionFragment.setArguments(bundle);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.descroption_container, descriptionFragment)
                    .commit();

        } else {
            stepsId = id;
            Bundle bundle = new Bundle();
            bundle.putInt(KEYID, stepsId);
            bundle.putBoolean(TWOPANELKEY, mTwoPane);
            DescriptionFragment descriptionFragment = new DescriptionFragment();
            descriptionFragment.setArguments(bundle);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, descriptionFragment)
                    .addToBackStack(null)
                    .commit();
        }
        fragmentId = 2;
    }
    //This method is used to open the previous video
    public void onClickPreviousStep(View view) {

        if (stepsId == 0) {
            stepsId = RecipeData.getStDescripton().size() - 1;

        } else {
            stepsId--;

        }
        setDescriptionFragment();

    }
    //This method is used to open the next video
    public void onClickNextStep(View view) {
        if (stepsId == RecipeData.getStDescripton().size() - 1) {
            stepsId = 0;
        } else {
            stepsId++;

        }
        setDescriptionFragment();
    }

    //This method is used to set up Steps Fragment
    public void setStepsFragment() {

        fragmentManager = getSupportFragmentManager();
        StepsFragment stepsFragment = new StepsFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, stepsFragment)
                .commit();

    }
    //This method is used to set up Steps Description
    public void setDescriptionFragment() {

        if (mTwoPane) {

        } else {
            Bundle bundle = new Bundle();
            bundle.putInt(KEYID, stepsId);
            DescriptionFragment descriptionFragment = new DescriptionFragment();
            descriptionFragment.setArguments(bundle);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, descriptionFragment)
                    .commit();

        }
    }
}
