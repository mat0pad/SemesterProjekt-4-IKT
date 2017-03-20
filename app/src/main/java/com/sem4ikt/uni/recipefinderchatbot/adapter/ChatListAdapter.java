package com.sem4ikt.uni.recipefinderchatbot.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.presenter.ChatListPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IChatListPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ChatListView;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/02/2017.
 */

public class ChatListAdapter extends BaseAdapter implements ChatListView {

    public static final int DIRECTION_INCOMING = 0;
    public static final int DIRECTION_OUTGOING = 1;
    public IChatListPresenter adapterPresenter;

    private List<Pair<String, Integer>> dialog;
    private Context mContext;

    public ChatListAdapter(List<Pair<String, Integer>> dialog, Context context){

        this.dialog = dialog;
        this.mContext = context;

        adapterPresenter = new ChatListPresenter(this);
    }

    public void onAddMessage(String m, int direction){

        dialog.add(new Pair<String, Integer>(m, direction));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dialog.size();
    }

    @Override
    public Pair<String, Integer> getItem(int i) {
        return dialog.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int i) {
        return dialog.get(i).second;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int direction = getItemViewType(position);
        ViewHolder holder;

        if (convertView == null || ((ViewHolder) convertView.getTag()).direction != direction) {

            int res = 0;
            int resid = 0;
            holder = new ViewHolder();

            if (direction == DIRECTION_INCOMING) {
                res = R.layout.message_l_box;
                resid = R.id.left_bubble_text;
                holder.direction = DIRECTION_INCOMING;
            }
            else {
                res = R.layout.message_r_box;
                resid = R.id.right_bubble_text;
                holder.direction = DIRECTION_OUTGOING;
            }
            convertView = LayoutInflater.from(mContext).inflate(res, parent, false);

            holder.text = (TextView) convertView.findViewById(resid);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(getItem(position).first);

        return convertView;
    }


    // Ensure that find by id is not called every time -> could cause slow scrolling
    private class ViewHolder {
        TextView text;
        int direction; // Used to check wether we have the right recylced view or need to inflate new holder
    }

}
