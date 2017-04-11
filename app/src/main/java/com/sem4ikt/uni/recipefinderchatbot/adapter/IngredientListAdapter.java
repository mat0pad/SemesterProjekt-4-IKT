package com.sem4ikt.uni.recipefinderchatbot.adapter;

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
    private List<IngredientsModel> list;
    private Context mContext;

    public IngredientListAdapter(Context context) {
        mContext = context;
        list = new ArrayList<>();
    }

    public void addItem(Object ingredientsModel) {
        list.add((IngredientsModel) ingredientsModel);
    }

    public void notifyUpdate() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public IngredientsModel getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        IngredientsModel i = list.get(position);

        if (convertView == null) {

            // Prevents calling (the resource heavy) findViewById every time the same view is loaded -> scrolling requires more resources otherwise
            holder = new IngredientListAdapter.ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(R.layout.recipe_list_cell_ingredients, parent, false);

            holder.title = (TextView) convertView.findViewById(R.id.recipe_list_title);
            holder.ingredientsMissed = (TextView) convertView.findViewById(R.id.recipe_list_ingredient_not);
            holder.ingredientsRight = (TextView) convertView.findViewById(R.id.recipe_list_ingredient);
            holder.image = (ImageView) convertView.findViewById(R.id.recipe_list_image);

            convertView.setTag(holder);
        } else {
            holder = (IngredientListAdapter.ViewHolder) convertView.getTag();
        }


        // check to see if each individual textview is null.
        // if not, assign some text!
        if (i != null) {

            if (holder.title != null)
                holder.title.setText(i.getTitle());

            if (holder.ingredientsMissed != null)
                holder.ingredientsMissed.setText(i.getMissedIngredientCount());

            if (holder.ingredientsRight != null)
                holder.ingredientsRight.setText(i.getUsedIngredientCount());

            if (holder.image != null)
                Picasso.with(mContext).load(BASE_URL + i.getImage()).fit().into(holder.image);
        }

        return convertView;
    }


    // Ensure that find by id is not called every time -> could cause slow scrolling
    private class ViewHolder {
        TextView title;
        TextView ingredientsMissed;
        TextView ingredientsRight;
        ImageView image;
    }

}

