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
import java.util.Iterator;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 19/03/2017.
 */

public class FavoritesGridAdapter extends BaseAdapter implements IFavoritesGridAdapterView {

    private List<RecipeModel> fullList;
    private List<RecipeModel> list;
    private Context mContext;
    private boolean isdeleting;

    public FavoritesGridAdapter(Context context){
        fullList = new ArrayList<>();
        list = new ArrayList<>();
        mContext = context;
        isdeleting = false;

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

        if(fullList.contains(recipe)) {
            fullList.remove(recipe);
            list.remove(recipe);
            Log.e("Recipe","CONTAINTS");
            return;
        }
        for(int i = 0; i < fullList.size();++i)
        {
            if(recipe.getId() == fullList.get(i).getId()) {
                fullList.remove(fullList.get(i));
                list.remove(fullList.get(i));
                Log.e("RECIPE","FOUNTBYITERATOR");
                break;
            }
        }
    }

    @Override
    public void setDeleting(boolean isdeleting) {
        this.isdeleting = isdeleting;
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
            holder.closeimage = (ImageView) convertView.findViewById(R.id.close_image_cell);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(getItem(position).getTitle());
        if(!isdeleting) {
            Picasso.with(mContext).load(getItem(position).getImage()).into(holder.image);
            holder.image.setImageAlpha(255);
            holder.closeimage.setVisibility(View.INVISIBLE);

        }
        else {
            Picasso.with(mContext).load(getItem(position).getImage()).into(holder.image);
            holder.image.setImageAlpha(100);
            holder.closeimage.setVisibility(View.VISIBLE);
        }

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
        ImageView closeimage;
    }
}
