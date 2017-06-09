package com.example.alprybysh.recipeapp.utilities;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import com.example.alprybysh.recipeapp.Recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import timber.log.Timber;

/**
 * Created by aprybysh on 5/12/17.
 */

public class JsonReaderHelper {

    private JSONObject jsonObject;
    private JSONArray array;
    private Recipe mRecipe;
    private JSONArray jsarr;
    private JSONArray ingridArray;


    private void loadJSONFromAsset(Context context) {


        StringBuilder buf;

        InputStream jsonInput;

        BufferedReader streamReader;

        try {

            buf = new StringBuilder();
            jsonInput = context.getAssets().open("recipes.json");
            streamReader = new BufferedReader(new InputStreamReader(jsonInput, "UTF-8"));
            String inputStr;

            while ((inputStr = streamReader.readLine()) != null) {
                buf.append(inputStr);
            }

            array = new JSONArray(buf.toString());

        } catch (IOException e) {

            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void loadRecipeDetails(int id) {


        if (array.length() == 0 || array == null) {
            return;
        }

        try {
            jsarr = array.getJSONObject(id).getJSONArray("steps");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void loadIngredients(int id) {

        if (array.length() == 0 || array == null) {
            return;
        }

        try {
            ingridArray = array.getJSONObject(id).getJSONArray("ingredients");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    //This method is used to get a name of recipe
    private ArrayList<String> getNameRecipe() {

        if (array.length() == 0 || array == null) {
            return null;
        }

        ArrayList<String> list = new ArrayList<>();

        try {
            for (int i = 0; i < array.length(); i++) {
                list.add(array.getJSONObject(i).getString("name"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Timber.v("Recipe names", list);

        return list;

    }

    //This method is used to get  a recipe ID
    private ArrayList<Integer> getIdRecipe() {

        if (array.length() == 0 || array == null) {
            return null;
        }

        ArrayList<Integer> list = new ArrayList<>();

        try {
            for (int i = 0; i < array.length(); i++) {

                list.add(array.getJSONObject(i).getInt("id"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Timber.v("Recipe ID", list);

        return list;

    }

    //This method is used to get servings
    private ArrayList<Integer> getServings() {

        if (array.length() == 0 || array == null) {
            return null;
        }

        ArrayList<Integer> list = new ArrayList<>();

        try {
            for (int i = 0; i < array.length(); i++) {

                list.add(array.getJSONObject(i).getInt("servings"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Timber.v("Servings", list);

        return list;

    }


    private ArrayList<Integer> getStepsID() {

        ArrayList<Integer> list = new ArrayList<>();

        if (jsarr.length() == 0 || jsarr == null) {
            return null;
        }

        try {
            for (int i = 0; i < jsarr.length(); i++) {
                list.add(jsarr.getJSONObject(i).getInt("id"));
                Timber.v(Integer.toString(jsarr.getJSONObject(i).getInt("id")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    private ArrayList<String> getShortDescription() {

        ArrayList<String> list = new ArrayList<>();

        if (jsarr.length() == 0 || jsarr == null) {
            return null;
        }

        try {
            for (int i = 0; i < jsarr.length(); i++) {
                list.add(jsarr.getJSONObject(i).getString("shortDescription"));
                Timber.v(jsarr.getJSONObject(i).getString("shortDescription"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    private ArrayList<String> getDescription() {

        ArrayList<String> list = new ArrayList<>();

        if (jsarr.length() == 0 || jsarr == null) {
            return null;
        }

        try {
            for (int i = 0; i < jsarr.length(); i++) {
                list.add(jsarr.getJSONObject(i).getString("description"));
                Timber.v(jsarr.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    private ArrayList<String> getVideoUrl() {

        ArrayList<String> list = new ArrayList<>();

        if (jsarr.length() == 0 || jsarr == null) {
            return null;
        }

        try {
            for (int i = 0; i < jsarr.length(); i++) {
                list.add(jsarr.getJSONObject(i).getString("videoURL"));
                Timber.v(jsarr.getJSONObject(i).getString("videoURL"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    private ArrayList<String> getQuntity() {

        ArrayList<String> list = new ArrayList<>();

        if (ingridArray.length() == 0 || ingridArray == null) {
            return null;
        }

        try {
            for (int i = 0; i < ingridArray.length(); i++) {
                list.add(ingridArray.getJSONObject(i).getString("quantity"));
                Timber.v(ingridArray.getJSONObject(i).getString("quantity"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    private ArrayList<String> getMeasure() {

        ArrayList<String> list = new ArrayList<>();

        if (ingridArray.length() == 0 || ingridArray == null) {
            return null;
        }

        try {
            for (int i = 0; i < ingridArray.length(); i++) {
                list.add(ingridArray.getJSONObject(i).getString("measure"));
                Timber.v(ingridArray.getJSONObject(i).getString("measure"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    private ArrayList<String> getIngredient() {

        ArrayList<String> list = new ArrayList<>();

        if (ingridArray.length() == 0 || ingridArray == null) {
            return null;
        }

        try {
            for (int i = 0; i < ingridArray.length(); i++) {
                list.add(ingridArray.getJSONObject(i).getString("ingredient"));
                Timber.v(ingridArray.getJSONObject(i).getString("ingredient"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }


    public ArrayList<Recipe> getRecipes(Context context) {

        loadJSONFromAsset(context);
        ArrayList<Recipe> list = new ArrayList<>();


        for (int i = 0; i < array.length(); i++) {
            mRecipe = new Recipe();

            mRecipe.setRecipeID(getIdRecipe().get(i));
            mRecipe.setRecipeName(getNameRecipe().get(i));
            mRecipe.setServing(getServings().get(i));
            list.add(mRecipe);

        }

        return list;
    }

    public Recipe getRecipeDetails(int id, Context context) {

        loadJSONFromAsset(context);
        loadRecipeDetails(id);
        loadIngredients(id);

        mRecipe = new Recipe();

        mRecipe.setStepsID(getStepsID());
        mRecipe.setShortDescription(getShortDescription());
        mRecipe.setDescripton(getDescription());
        mRecipe.setVideoURL(getVideoUrl());
        mRecipe.setQuantity(getQuntity());
        mRecipe.setMeasure(getMeasure());
        mRecipe.setIngredient(getIngredient());

        return mRecipe;
    }


}
