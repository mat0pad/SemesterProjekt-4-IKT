package com.sem4ikt.uni.recipefinderchatbot.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.IngredientsModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IRecipeAdapterListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henriknielsen on 06/04/2017.
 */

public class IngredientListAdapter extends BaseAdapter implements IRecipeAdapterListView {


    private static String BASE_URL = "https://spoonacular.com/recipeImages/";
    private List<IngredientsModel> ingredientsModels;
    private Context mContext;

    public IngredientListAdapter(Context context) {
        mContext = context;
        ingredientsModels = new ArrayList<>();
    }

    public void addItem(Object ingredientsModel) {
        ingredientsModels.add((IngredientsModel) ingredientsModel);
    }

    public void notifyUpdate() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return ingredientsModels.size();
    }

    @Override
    public IngredientsModel getItem(int position) {
        return ingredientsModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        IngredientListAdapter.ViewHolder holder;
        IngredientsModel i = ingredientsModels.get(position);

        if (convertView == null) {

            // Prevents calling (the resource heavy) findViewById every time the same view is loaded -> scrolling requires more resources otherwise
            holder = new IngredientListAdapter.ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(R.layout.recipe_list_cell, parent, false);


            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.
            holder.title = (TextView) convertView.findViewById(R.id.recipe_list_title);
            holder.ingredientsCount = (TextView) convertView.findViewById(R.id.recipe_list_readyInMinutes);
            holder.image = (ImageView) convertView.findViewById(R.id.recipe_list_image);

            convertView.setTag(holder);
        } else {
            holder = (IngredientListAdapter.ViewHolder) convertView.getTag();
        }


        // check to see if each individual textview is null.
        // if not, assign some text!

        if (i != null) {
            if (holder.title != null) {
                holder.title.setText(i.getTitle());
            }
            if (holder.ingredientsCount != null) {

                int count = i.getUsedIngredientCount();

                String conjugation;

                if(count == 1)
                {
                    conjugation = " ingredient";
                }
                else
                {
                    conjugation = " ingredients";
                }

                holder.ingredientsCount.setText("Contains " + count + conjugation + " you specified");
            }
            if (holder.image != null) {
                Picasso.with(mContext).load(BASE_URL + i.getImage()).fit().into(holder.image);
            }
        }

        // the view must be returned to our activity
        return convertView;

    }


    // Ensure that find by id is not called every time -> could cause slow scrolling
    private class ViewHolder {
        TextView title;
        TextView ingredientsCount;
        ImageView image;
    }

}

