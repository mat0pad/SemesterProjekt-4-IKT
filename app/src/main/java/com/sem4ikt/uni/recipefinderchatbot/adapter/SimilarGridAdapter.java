package com.sem4ikt.uni.recipefinderchatbot.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.activity.DetailRecipeActivity;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.view.ISimilarGridAdapterView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public class SimilarGridAdapter extends BaseAdapter implements ISimilarGridAdapterView {

    private static String BASE_URL = "https://spoonacular.com/recipeImages/";
    private List<RecipesModel> list;
    private Context mContext;

    public SimilarGridAdapter(Context context) {
        list = new ArrayList<>();
        mContext = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RecipesModel getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(R.layout.similar_cell, parent, false);

            holder.text = (TextView) convertView.findViewById(R.id.text_cell);
            holder.image = (ImageView) convertView.findViewById(R.id.similar_image_cell);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Set title/name
        holder.text.setText(getItem(position).getTitle());

        // Load image
        Picasso.with(mContext).load(BASE_URL + getItem(position).getImage()).fit().into(holder.image);


        return convertView;
    }

    @Override
    public void showSimilar(int id) {

        System.out.println("Selected recipe id is " + id);

        // Show recipe with id
        Intent intent = new Intent(mContext, DetailRecipeActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt("id", id); // pass id

        intent.putExtras(bundle); // Put id to Intent

        mContext.startActivity(intent);
    }

    @Override
    public void notifyUpdate() {
        notifyDataSetChanged();
    }

    @Override
    public void addItem(RecipesModel item) {
        list.add(item);
    }



    // Ensure that find by id is not called every time -> could cause slow scrolling
    private class ViewHolder {
        TextView text;
        ImageView image;
    }
}
