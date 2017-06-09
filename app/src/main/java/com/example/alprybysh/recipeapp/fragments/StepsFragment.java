package com.example.alprybysh.recipeapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alprybysh.recipeapp.R;
import com.example.alprybysh.recipeapp.RecipeDetails;
import com.example.alprybysh.recipeapp.data.RecipeData;
import com.example.alprybysh.recipeapp.utilities.FetchRecipesDetails;
import com.example.alprybysh.recipeapp.utilities.StepsAdapter;

import java.util.ArrayList;

/**
 * Created by aprybysh on 5/16/17.
 */

public class StepsFragment extends Fragment implements StepsAdapter.StepsOnClickListenerHandler {

    Context context;
    private RecyclerView mRecyclerView;
    private StepsAdapter adapter;
    private ArrayList<String> mShortDescription;
    private static final String INGREDIENTS = "INGREDIENTS:";

    private OnRecipeSelectedListener mListener;

    public StepsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipe_steps_fragment, container, false);


        context = getActivity();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view_fragment);

        /*Setting the LinearLayoutManager as a manager for the RecyclerView*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new StepsAdapter(context, this);


        TextView ingred = (TextView) rootView.findViewById(R.id.ingredients_view);
        TextView ingTitle = (TextView) rootView.findViewById(R.id.ingredients_title);

        String str = " ";
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < RecipeData.getStIngredient().size(); i++) {
            builder.append(RecipeData.getStQuantity().get(i));
            builder.append(str);
            builder.append(RecipeData.getStMeasure().get(i));
            builder.append(str);
            builder.append(RecipeData.getStIngredient().get(i));
            builder.append("." + str);

        }

        ingTitle.setText(INGREDIENTS);
        ingred.setText(builder);

         /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mRecyclerView.setAdapter(adapter);
        // recipesDetails = new FetchRecipesDetails(context);
        //recipesDetails.execute(getArguments().getInt("id"));

        mShortDescription = new ArrayList<>();
        mShortDescription = RecipeData.getStShortDescription();

        adapter.setDataSteps(mShortDescription);


        return rootView;
    }


    @Override
    public void onItemClick(ArrayList<String> itemClicked, int position) {

        Toast.makeText(context, itemClicked.get(position), Toast.LENGTH_SHORT).show();

        mListener.onRecipeSelected(position);

    }


    public interface OnRecipeSelectedListener {
        public void onRecipeSelected(int id);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnRecipeSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }
    }


}
