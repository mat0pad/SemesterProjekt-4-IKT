package com.sem4ikt.uni.recipefinderchatbot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IRecipeAdapterListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henriknielsen on 30/03/2017.
 */

public class RecipeListAdapter extends BaseAdapter implements IRecipeAdapterListView {

    private static String BASE_URL = "https://spoonacular.com/recipeImages/";
    private List<RecipesModel> list;
    private Context mContext;

    public RecipeListAdapter(Context context) {
        mContext = context;
        list = new ArrayList<>();
    }

    public void addItem(Object recipesModel) {
        list.add((RecipesModel) recipesModel);
    }

    public void notifyUpdate() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RecipesModel getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        RecipesModel i = list.get(position);

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(R.layout.recipe_list_cell_recipes, parent, false);

            holder.title = (TextView) convertView.findViewById(R.id.recipe_list_title);
            holder.readyInMinutes = (TextView) convertView.findViewById(R.id.recipe_list_readyInMinutes);
            holder.image = (ImageView) convertView.findViewById(R.id.recipe_list_image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (i != null) {
            if (holder.title != null)
                holder.title.setText(i.getTitle());

            if (holder.readyInMinutes != null)
                holder.readyInMinutes.setText(i.getReadyInMinutes().toString() + " min");

            if (holder.image != null)
                Picasso.with(mContext).load(BASE_URL + i.getImage()).fit().into(holder.image);
        }

        return convertView;
    }


    // Ensure that find by id is not called every time -> could cause slow scrolling
    private class ViewHolder {
        TextView title;
        TextView readyInMinutes;
        ImageView image;
    }

}

