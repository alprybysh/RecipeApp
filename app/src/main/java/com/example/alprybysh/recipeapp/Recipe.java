package com.example.alprybysh.recipeapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by aprybysh on 5/14/17.
 */

public class Recipe implements Parcelable {

    private int recipeID;
    private String recipeName;
    private int serving;
    private ArrayList<Integer> stepsID;
    private ArrayList<String> shortDescription;
    private ArrayList<String> descripton;
    private ArrayList<String> videoURL;
    private ArrayList<String> quantity;
    private ArrayList<String> measure;
    private ArrayList<String> ingredient;


    public Recipe() {
    }


    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

    public ArrayList<Integer> getStepsID() {
        return stepsID;
    }

    public void setStepsID(ArrayList<Integer> stepsID) {
        this.stepsID = stepsID;
    }

    public ArrayList<String> getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(ArrayList<String> shortDescription) {
        this.shortDescription = shortDescription;
    }

    public ArrayList<String> getDescripton() {
        return descripton;
    }

    public void setDescripton(ArrayList<String> descripton) {
        this.descripton = descripton;
    }

    public ArrayList<String> getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(ArrayList<String> videoURL) {
        this.videoURL = videoURL;
    }

    public ArrayList<String> getQuantity() {
        return quantity;
    }

    public void setQuantity(ArrayList<String> quantity) {
        this.quantity = quantity;
    }

    public ArrayList<String> getMeasure() {
        return measure;
    }

    public void setMeasure(ArrayList<String> measure) {
        this.measure = measure;
    }

    public ArrayList<String> getIngredient() {
        return ingredient;
    }

    public void setIngredient(ArrayList<String> ingredient) {
        this.ingredient = ingredient;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(recipeID);
        dest.writeInt(serving);
        dest.writeString(recipeName);
        dest.writeSerializable(shortDescription);
        dest.writeSerializable(stepsID);
        dest.writeSerializable(videoURL);
        dest.writeSerializable(descripton);
        dest.writeSerializable(quantity);
        dest.writeSerializable(measure);
        dest.writeSerializable(ingredient);


    }


    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {

        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };


    Recipe(Parcel in) {

        recipeID = in.readInt();
        serving = in.readInt();
        recipeName = in.readString();
        shortDescription = (ArrayList<String>) in.readSerializable();
        stepsID = (ArrayList<Integer>) in.readSerializable();
        videoURL = (ArrayList<String>) in.readSerializable();
        descripton = (ArrayList<String>) in.readSerializable();
        quantity = (ArrayList<String>) in.readSerializable();
        measure = (ArrayList<String>) in.readSerializable();
        ingredient = (ArrayList<String>) in.readSerializable();


    }


}
