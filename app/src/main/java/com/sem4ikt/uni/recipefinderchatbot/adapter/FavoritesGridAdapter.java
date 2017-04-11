package com.sem4ikt.uni.recipefinderchatbot.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.activity.DetailRecipeActivity;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IFavoritesGridAdapterView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 19/03/2017.
 */

public class FavoritesGridAdapter extends BaseAdapter implements IFavoritesGridAdapterView {

    private List<RecipeModel> fullList;
    private List<RecipeModel> list;
    private Context mContext;

    public FavoritesGridAdapter(Context context){
        fullList = new ArrayList<>();
        list = new ArrayList<>();
        mContext = context;

    }

    @Override
    public int getCount() {
        if(list == null)
            return 0;
        return list.size();
    }

    @Override
    public RecipeModel getItem(int i) {
        if(list == null)
            return null;
        return list.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void showRecipe(RecipeModel recipe) {
        Intent intent = new Intent(mContext, DetailRecipeActivity.class);
        intent.putExtra("recipe",recipe);
        mContext.startActivity(intent);
    }

    @Override
    public void deleteRecipe(RecipeModel recipe) {
        fullList.remove(recipe);
        Log.e("fullList.toString()",fullList.toString());
    }


    @Override
    public List<RecipeModel> getList(){
        return fullList;
    }

    @Override
    public void addRecipe(RecipeModel recipe) {
        fullList.add(recipe);
    }


    @Override
    public void setList(List<RecipeModel> list) {
        this.list = list;
    }

    @Override
    public void setFullList(List<RecipeModel> list) {
        fullList = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(R.layout.favorites_cell, parent, false);

            holder.text = (TextView) convertView.findViewById(R.id.text_cell);
            holder.image = (ImageView) convertView.findViewById(R.id.favorite_image_cell);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(getItem(position).getTitle());
        Picasso.with(mContext).load(getItem(position).getImage()).into(holder.image);

        return convertView;
    }

    @Override
    public void notifyUpdate() {
        notifyDataSetChanged();
    }

    // Ensure that find by id is not called every time -> could cause slow scrolling
    private class ViewHolder {
        TextView text;
        ImageView image;
    }
}
