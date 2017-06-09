package com.example.alprybysh.recipeapp.utilities;

import com.example.alprybysh.recipeapp.R;
import com.example.alprybysh.recipeapp.Recipe;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aprybysh on 5/17/17.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    Context context;
    private Recipe mRecipe;
    private ArrayList<String> mShorstdescrip;
    private static final String STEPS = "Step ";


    private final StepsOnClickListenerHandler mStepsOnClickListenerHandler;

    public StepsAdapter(@NonNull Context context, StepsOnClickListenerHandler mStepsOnClickListenerHandler) {

        this.context = context;
        this.mStepsOnClickListenerHandler = mStepsOnClickListenerHandler;
    }

    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.step_short_description, parent, false);

        view.setFocusable(true);


        return new StepsViewHolder(view);


    }

    @Override
    public void onBindViewHolder(StepsViewHolder holder, int position) {

        TextView stepId = holder.stepID;
        TextView shortDescrip = holder.shortDescription;
        shortDescrip.setText(mShorstdescrip.get(position));

        if (position == 0) {
            stepId.setText(STEPS + " Introduction");
        } else {
            stepId.setText(STEPS + Integer.toString(position));
        }


    }

    @Override
    public int getItemCount() {
        if (mShorstdescrip == null || mShorstdescrip.size() == 0) return 0;

        return mShorstdescrip.size();
    }

    public interface StepsOnClickListenerHandler {
        void onItemClick(ArrayList<String> itemClicked, int position);
    }


    public void setDataSteps(ArrayList<String> shortDescription) {

        mShorstdescrip = shortDescription;
        notifyDataSetChanged();
    }


    public class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView stepID;
        TextView shortDescription;


        public StepsViewHolder(View itemView) {

            super(itemView);

            stepID = (TextView) itemView.findViewById(R.id.recipe_single_step_id);
            shortDescription = (TextView) itemView.findViewById(R.id.rescipe_single_step_shortdescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mStepsOnClickListenerHandler.onItemClick(mShorstdescrip, getAdapterPosition());

        }
    }
}
