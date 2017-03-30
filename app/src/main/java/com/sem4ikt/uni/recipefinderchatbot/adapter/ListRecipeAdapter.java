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
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IListRecipeAdapterView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henriknielsen on 30/03/2017.
 */

public class ListRecipeAdapter extends BaseAdapter implements IListRecipeAdapterView {


    private List<RecipesModel> recipesModels;
    private Context mContext;

    public ListRecipeAdapter(Context context) {
        mContext = context;
        recipesModels = new ArrayList<>();
    }

    public void addItem(RecipesModel recipesModel) {
        recipesModels.add(recipesModel);
    }

    public void update() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return recipesModels.size();
    }

    @Override
    public RecipesModel getItem(int position) {
        return recipesModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        RecipesModel i = recipesModels.get(position);

        if (convertView == null) {

            holder = new ViewHolder();

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
                holder.description = (TextView) convertView.findViewById(R.id.recipe_list_description);
                holder.image = (ImageView) convertView.findViewById(R.id.recipe_list_image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        // check to see if each individual textview is null.
        // if not, assign some text!

        if (i != null) {
            if (holder.title != null) {
                holder.title.setText(i.getTitle());
            }
            if (holder.description != null) {
                holder.description.setText(i.getId().toString());
            }

            if (holder.image != null) {
                Picasso.with(mContext).load(i.getImage()).fit().into(holder.image);
            }
        }

        // the view must be returned to our activity
        return convertView;

    }


    // Ensure that find by id is not called every time -> could cause slow scrolling
    private class ViewHolder {
        TextView title;
        TextView description;
        ImageView image;
    }

}

