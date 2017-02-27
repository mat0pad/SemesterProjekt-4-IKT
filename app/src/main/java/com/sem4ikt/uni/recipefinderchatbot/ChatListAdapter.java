package com.sem4ikt.uni.recipefinderchatbot;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/02/2017.
 */

public class ChatListAdapter extends BaseAdapter {

    private List<String> dialog;
    private Context mContext;

    ChatListAdapter(List<String> dialog, Context context){

        this.dialog = dialog;
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return dialog.size();
    }

    @Override
    public Object getItem(int i) {
        return dialog.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){

            holder = new ViewHolder();

            if(position % 2 == 0) {

                convertView = LayoutInflater.from(mContext).inflate(R.layout.message_l_box, parent, false);

                holder.text = (TextView) convertView.findViewById(R.id.left_bubble_text);
            }
            else{
                convertView = LayoutInflater.from(mContext).inflate(R.layout.message_r_box, parent, false);

                holder.text = (TextView) convertView.findViewById(R.id.right_bubble_text);
            }

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(getItem(position).toString());
        return convertView;
    }


    // Ensure that find by id is not called every time -> could cause slow scrolling

    private class ViewHolder {
        TextView text;
    }

}
