package com.sem4ikt.uni.recipefinderchatbot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.EquipmentModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IEquipmentsGridAdapterView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 30/03/2017.
 */

public class EquipmentsGridAdapter extends BaseAdapter implements IEquipmentsGridAdapterView {

    private List<EquipmentModel> list;
    private Context mContext;

    public EquipmentsGridAdapter(Context context) {
        list = new ArrayList<>();
        mContext = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public EquipmentModel getItem(int i) {
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

            convertView = LayoutInflater.from(mContext).inflate(R.layout.equipment_cell, parent, false);

            holder.name = (TextView) convertView.findViewById(R.id.equipment_name_cell);
            holder.image = (ImageView) convertView.findViewById(R.id.equipment_image_cell);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getName());
        // Load image
        Picasso.with(mContext).load(getItem(position).getImage()).into(holder.image);

        return convertView;
    }

    @Override
    public void notifyUpdate() {
        notifyDataSetChanged();
    }

    @Override
    public void addItem(EquipmentModel item) {
        list.add(item);
    }

    // Ensure that find by id is not called every time -> could cause slow scrolling
    private class ViewHolder {
        TextView name;
        ImageView image;
    }
}
