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

public class RecipeListAdapter extends BaseAdapter implements IListRecipeAdapterView {


    private List<RecipesModel> recipesModels;
    private Context mContext;

    public RecipeListAdapter(Context context) {
        mContext = context;
        recipesModels = new ArrayList<>();
    }

    public void addItem(RecipesModel recipesModel) {
        recipesModels.add(recipesModel);
    }

    public void notifyUpdate() {
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

            convertView = LayoutInflater.from(mContext).inflate(R.layout.recipe_list_cell, parent, false);

            holder.title = (TextView) convertView.findViewById(R.id.recipe_list_title);
            holder.description = (TextView) convertView.findViewById(R.id.recipe_list_description);
            holder.image = (ImageView) convertView.findViewById(R.id.recipe_list_image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

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

        return convertView;

    }


    // Ensure that find by id is not called every time -> could cause slow scrolling
    private class ViewHolder {
        TextView title;
        TextView description;
        ImageView image;
    }

}

