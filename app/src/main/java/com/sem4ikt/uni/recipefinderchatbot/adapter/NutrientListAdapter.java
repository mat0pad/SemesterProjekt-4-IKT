package com.sem4ikt.uni.recipefinderchatbot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.NutrientsModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IRecipeAdapterListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henriknielsen on 06/04/2017.
 */

public class NutrientListAdapter extends BaseAdapter implements IRecipeAdapterListView {

    private static String BASE_URL = "https://spoonacular.com/recipeImages/";
    private List<NutrientsModel> list;
    private Context mContext;

    public NutrientListAdapter(Context context) {
        mContext = context;
        list = new ArrayList<>();
    }

    public void addItem(Object nutrientsModels) {
        this.list.add((NutrientsModel) nutrientsModels);
    }

    public void notifyUpdate() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NutrientsModel getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        NutrientsModel i = list.get(position);

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(R.layout.recipe_list_cell_nutrients, parent, false);

            holder.title = (TextView) convertView.findViewById(R.id.recipe_list_title);
            holder.calories = (TextView) convertView.findViewById(R.id.recipe_list_calc);
            holder.carbs = (TextView) convertView.findViewById(R.id.recipe_list_carbs);
            holder.protein = (TextView) convertView.findViewById(R.id.recipe_list_protein);
            holder.image = (ImageView) convertView.findViewById(R.id.recipe_list_image);

            convertView.setTag(holder);
        } else {
            holder = (NutrientListAdapter.ViewHolder) convertView.getTag();
        }

        if (i != null) {
            if (holder.title != null)
                holder.title.setText(i.getTitle());

            if (holder.calories != null)
                holder.calories.setText(i.getCalories());

            if (holder.carbs != null)
                holder.carbs.setText(i.getCarbs());

            if (holder.carbs != null)
                holder.carbs.setText(i.getProtein());

            if (holder.image != null)
                Picasso.with(mContext).load(BASE_URL + i.getImage()).fit().into(holder.image);
        }

        // the view must be returned to our activity
        return convertView;
    }


    // Ensure that find by id is not called every time -> could cause slow scrolling
    private class ViewHolder {
        TextView title;
        TextView calories;
        TextView carbs;
        TextView protein;
        ImageView image;
    }

}