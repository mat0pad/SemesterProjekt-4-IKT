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
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.NutrientsDataModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IListAdapterView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henriknielsen on 06/04/2017.
 */

public class ListNutrientAdapter extends BaseAdapter implements IListAdapterView {


    private static String BASE_URL = "https://spoonacular.com/recipeImages/";
    private List<NutrientsDataModel> nutrientsModels;
    private Context mContext;

    public ListNutrientAdapter(Context context) {
        mContext = context;
        nutrientsModels = new ArrayList<>();
    }

    public void addItem(Object nutrientsModels) {
        this.nutrientsModels.add((NutrientsDataModel) nutrientsModels);
    }

    public void notifyUpdate() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return nutrientsModels.size();
    }

    @Override
    public NutrientsDataModel getItem(int position) {
        return nutrientsModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListNutrientAdapter.ViewHolder holder;
        NutrientsDataModel i = nutrientsModels.get(position);

        if (convertView == null) {

            holder = new ListNutrientAdapter.ViewHolder();

            // assign the view we are converting to a local variable


            // first check to see if the view is null. if so, we have to inflate it.
            // to inflate it basically means to render, or show, the view.
            convertView = LayoutInflater.from(mContext).inflate(R.layout.recipe_list_cell, parent, false);

		/*
         * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */


            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.
            holder.title = (TextView) convertView.findViewById(R.id.recipe_list_title);
            holder.calories = (TextView) convertView.findViewById(R.id.recipe_list_readyInMinutes);
            holder.image = (ImageView) convertView.findViewById(R.id.recipe_list_image);

            convertView.setTag(holder);
        } else {
            holder = (ListNutrientAdapter.ViewHolder) convertView.getTag();
        }


        // check to see if each individual textview is null.
        // if not, assign some text!

        if (i != null) {
            if (holder.title != null) {
                holder.title.setText(i.getTitle());
            }
            if (holder.calories != null) {

                int count = i.getCalories();

                String conjugation;

                if(count == 1)
                {
                    conjugation = " calorie";
                }
                else
                {
                    conjugation = " calories";
                }

                holder.calories.setText("Contains " + count + conjugation);
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
        TextView calories;
        ImageView image;
    }

}