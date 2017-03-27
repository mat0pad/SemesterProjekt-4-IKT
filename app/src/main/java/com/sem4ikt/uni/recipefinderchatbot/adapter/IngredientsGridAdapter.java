package com.sem4ikt.uni.recipefinderchatbot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.ExtendedIngredientModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public class IngredientsGridAdapter extends BaseAdapter {

    public List<ExtendedIngredientModel> list;
    private Context mContext;

    public IngredientsGridAdapter(Context context, List<ExtendedIngredientModel> list) {
        this.list = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ExtendedIngredientModel getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(R.layout.ingredient_cell, parent, false);

            holder.name = (TextView) convertView.findViewById(R.id.ingredient_name_cell);
            holder.amount = (TextView) convertView.findViewById(R.id.ingredient_amount_cell);
            holder.image = (ImageView) convertView.findViewById(R.id.ingredient_image_cell);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getName());
        holder.amount.setText(getItem(position).getAmount().toString() + " " + getItem(position).getUnitLong());

        Picasso.with(mContext).load(getItem(position).getImage()).into(holder.image);

        return convertView;
    }

    // Ensure that find by id is not called every time -> could cause slow scrolling
    private class ViewHolder {
        TextView name, amount;
        ImageView image;
    }
}
