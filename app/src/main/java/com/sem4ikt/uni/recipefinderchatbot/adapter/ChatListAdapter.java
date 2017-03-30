package com.sem4ikt.uni.recipefinderchatbot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;
import com.sem4ikt.uni.recipefinderchatbot.view.ChatListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/02/2017.
 */

public class ChatListAdapter extends BaseAdapter implements ChatListView {

    public static final int DIRECTION_INCOMING = 0;
    public static final int DIRECTION_OUTGOING = 1;

    private List<MessageModel> dialog;
    private Context mContext;

    public ChatListAdapter(Context context) {

        dialog = new ArrayList<>();
        this.mContext = context;
    }

    public void onAddMessage(MessageModel m) {

        dialog.add(m);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dialog.size();
    }

    @Override
    public MessageModel getItem(int i) {
        return dialog.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int i) {
        return dialog.get(i).direction;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MessageModel.TYPE type = dialog.get(position).type;
        int direction = getItemViewType(position);
        ViewHolder holder;

        if (convertView == null || ((ViewHolder) convertView.getTag()).direction != direction
                || ((ViewHolder) convertView.getTag()).type != type.ordinal()) {

            int res, resid;
            holder = new ViewHolder();

            if (direction == DIRECTION_INCOMING) {

                if (type == MessageModel.TYPE.SINGLE_RECIPE) {
                    res = R.layout.message_l_box_recipe;
                } else {
                    res = R.layout.message_l_box;
                }
                resid = R.id.left_bubble_text;
                holder.direction = DIRECTION_INCOMING;
                holder.type = type.ordinal();
            }
            else {
                res = R.layout.message_r_box;
                resid = R.id.right_bubble_text;
                holder.direction = DIRECTION_OUTGOING;
            }
            convertView = LayoutInflater.from(mContext).inflate(res, parent, false);

            holder.text = (TextView) convertView.findViewById(resid);

            if (type == MessageModel.TYPE.SINGLE_RECIPE)
                holder.image = (ImageView) convertView.findViewById(R.id.message_l_image);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(getItem(position).message);

        if (holder.type == MessageModel.TYPE.SINGLE_RECIPE.ordinal())
            Picasso.with(mContext).load(getItem(position).image).into(holder.image);

        return convertView;
    }


    // Ensure that find by id is not called every time -> could cause slow scrolling
    private class ViewHolder {
        TextView text;
        ImageView image;
        int type;
        int direction; // Used to check wether we have the right recylced view or need to inflate new holder
    }

}
