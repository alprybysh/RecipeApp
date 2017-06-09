package com.example.alprybysh.recipeapp.data;

import java.util.ArrayList;

/**
 * Created by aprybysh on 5/18/17.
 */

public class RecipeData {

    private static int stRecipeID;
    private static String stRecipeName;
    private static int stServing;
    private static ArrayList<Integer> stStepsID;
    private static ArrayList<String> stShortDescription;
    private static ArrayList<String> stDescripton;

    private static ArrayList<String> stVideoURL;
    private static ArrayList<String> stQuantity;
    private static ArrayList<String> stMeasure;
    private static ArrayList<String> stIngredient;

    public static int getStRecipeID() {
        return stRecipeID;
    }

    public static void setStRecipeID(int stRecipeID) {
        RecipeData.stRecipeID = stRecipeID;
    }

    public static String getStRecipeName() {
        return stRecipeName;
    }

    public static void setStRecipeName(String stRecipeName) {
        RecipeData.stRecipeName = stRecipeName;
    }

    public static int getStServing() {
        return stServing;
    }

    public static void setStServing(int stServing) {
        RecipeData.stServing = stServing;
    }

    public static ArrayList<Integer> getStStepsID() {
        return stStepsID;
    }

    public static void setStStepsID(ArrayList<Integer> stStepsID) {
        RecipeData.stStepsID = stStepsID;
    }

    public static ArrayList<String> getStShortDescription() {
        return stShortDescription;
    }

    public static void setStShortDescription(ArrayList<String> stShortDescription) {
        RecipeData.stShortDescription = stShortDescription;
    }

    public static ArrayList<String> getStDescripton() {
        return stDescripton;
    }

    public static void setStDescripton(ArrayList<String> stDescripton) {
        RecipeData.stDescripton = stDescripton;
    }

    public static ArrayList<String> getStVideoURL() {
        return stVideoURL;
    }

    public static void setStVideoURL(ArrayList<String> stVideoURL) {
        RecipeData.stVideoURL = stVideoURL;
    }

    public static ArrayList<String> getStQuantity() {
        return stQuantity;
    }

    public static void setStQuantity(ArrayList<String> stQuantity) {
        RecipeData.stQuantity = stQuantity;
    }

    public static ArrayList<String> getStMeasure() {
        return stMeasure;
    }

    public static void setStMeasure(ArrayList<String> stMeasure) {
        RecipeData.stMeasure = stMeasure;
    }

    public static ArrayList<String> getStIngredient() {
        return stIngredient;
    }

    public static void setStIngredient(ArrayList<String> stIngredient) {
        RecipeData.stIngredient = stIngredient;
    }


}
