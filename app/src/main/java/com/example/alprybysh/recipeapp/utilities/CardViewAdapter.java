package com.example.alprybysh.recipeapp.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.alprybysh.recipeapp.R;
import com.example.alprybysh.recipeapp.Recipe;

import java.util.List;

/**
 * Created by aprybysh on 5/12/17.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.RecipeViewHolder> {

    Context context;

    private List<Recipe> recipes;
    private Recipe mRecipe;

    private final RecipeOnClickListenerHandler mRecipeOnClickListenerHandler;

    public CardViewAdapter(@NonNull Context context, RecipeOnClickListenerHandler clickListenerHandler) {
        this.context = context;
        mRecipeOnClickListenerHandler = clickListenerHandler;
    }

    /**
     * The interface that receives onClick messages.
     */

    public interface RecipeOnClickListenerHandler {
        void onItemClick(Recipe itemClicked);
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent   The ViewGroup that these ViewHolders are contained within.
     * @param viewType If your RecyclerView has more than one type of item (which ours doesn't) you
     *                 can use this viewType integer to provide a different layout. See
     *                 {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                 for more details.
     * @return A new RecipeViewHolder that holds the View for each list item
     */


    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.cards_view, parent, false);

        view.setFocusable(true);


        return new RecipeViewHolder(view);

    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the weather
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param RecipeViewHolder The ViewHolder which should be updated to represent the
     *                         contents of the item at the given position in the data set.
     * @param position         The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

        TextView textViewName = holder.textViewName;
        mRecipe = recipes.get(position);
        textViewName.setText(mRecipe.getRecipeName());


    }

    /**
     * This method simply returns the number of items to display.
     */
    @Override
    public int getItemCount() {
        if (recipes == null || recipes.size() == 0) return 0;

        return recipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName;


        public RecipeViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.single_recipe_card);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            mRecipe = new Recipe();
            mRecipe = recipes.get(getAdapterPosition());
            mRecipeOnClickListenerHandler.onItemClick(mRecipe);


        }
    }

    public void setCardAdapterDate(List<Recipe> recipes) {

        if (recipes == null) {
            this.recipes = recipes;
            return;
        }
        this.recipes = recipes;
        notifyDataSetChanged();
    }
}
