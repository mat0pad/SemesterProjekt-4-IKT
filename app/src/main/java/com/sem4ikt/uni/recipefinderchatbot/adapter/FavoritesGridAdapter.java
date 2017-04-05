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

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 19/03/2017.
 */

public class FavoritesGridAdapter extends BaseAdapter {

    private List<RecipesModel> list;
    private Context mContext;

    public FavoritesGridAdapter(Context context, List<RecipesModel> list){
        this.list = list;
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

    public List<RecipesModel> getList(){
        return list;
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

        return convertView;
    }

    // Ensure that find by id is not called every time -> could cause slow scrolling
    private class ViewHolder {
        TextView text;
        ImageView image;
    }
}
