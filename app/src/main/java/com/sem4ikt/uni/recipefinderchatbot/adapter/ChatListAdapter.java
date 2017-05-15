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
import com.sem4ikt.uni.recipefinderchatbot.activity.ListDataModelActivity;
import com.sem4ikt.uni.recipefinderchatbot.model.MessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.MoreRecipeMessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.SingleRecipeMessageModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.IngredientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.NutrientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IChatListAdapterView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 27/02/2017.
 */

public class ChatListAdapter extends BaseAdapter implements IChatListAdapterView {

    public static final int DIRECTION_INCOMING = 0;
    public static final int DIRECTION_OUTGOING = 1;

    private List<MessageModel> dialog;
    private Context mContext;

    public ChatListAdapter(Context context) {

        dialog = new ArrayList<>();
        this.mContext = context;
    }

    private static String getImage(MessageModel model) {

        String image;

        switch (model.type) {

            case SINGLE_RECIPE:
                image = ((SingleRecipeMessageModel) model).image;
                break;

            default:
                image = ((MoreRecipeMessageModel) model).image;
                break;
        }

        return image;
    }

    @Override
    public void onAddMessage(MessageModel m) {

        dialog.add(m);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(int position) {

        MessageModel model = getItem(position);

        if (model != null && model.type != MessageModel.TYPE.NORMAL) {

            if (model.type == MessageModel.TYPE.SINGLE_RECIPE) {

                Bundle bundle = new Bundle();
                bundle.putInt("id", ((SingleRecipeMessageModel) model).id); // pass id to detail

                mContext.startActivity(new Intent(mContext, DetailRecipeActivity.class).putExtras(bundle));
            } else {

                Intent intent = new Intent(mContext, ListDataModelActivity.class);

                MoreRecipeMessageModel mm = (MoreRecipeMessageModel) model;

                if (model.type == MessageModel.TYPE.MORE_RECIPES_MODEL) {

                    ListDataContainer container = ListContainerFactory
                            .createRecipesListContainer((List<RecipesModel>) mm.obj);

                    intent.putExtra("listOfRecipesModels", container);

                    mContext.startActivity(intent);

                } else if (model.type == MessageModel.TYPE.MORE_INGREDIENTS_MODEL) {

                    ListDataContainer container = ListContainerFactory
                            .createIngredientsListContainer((List<IngredientsModel>) mm.obj);

                    intent.putExtra("listOfRecipesModels", container);

                    mContext.startActivity(intent);

                } else if (model.type == MessageModel.TYPE.MORE_NUTRIENTS_MODEL) {

                    ListDataContainer container = ListContainerFactory
                            .createNutrientsListContainer((List<NutrientsModel>) mm.obj);

                    intent.putExtra("listOfRecipesModels", container);

                    mContext.startActivity(intent);

                } else if (model.type == MessageModel.TYPE.MORE_RECIPE_MODEL) {

                    ListDataContainer container = ListContainerFactory
                            .createRecipeListContainer((List<RecipeModel>) mm.obj);

                    intent.putExtra("listOfRecipesModels", container);

                    mContext.startActivity(intent);
                }
            }
        }
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

                if (type != MessageModel.TYPE.NORMAL)
                    res = R.layout.message_l_box_recipe;
                else
                    res = R.layout.message_l_box;

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

            if (type != MessageModel.TYPE.NORMAL)
                holder.image = (ImageView) convertView.findViewById(R.id.message_l_image);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(getItem(position).message);

        if (holder.type != MessageModel.TYPE.NORMAL.ordinal()) {

            Picasso.with(mContext).load(getImage(getItem(position))).into(holder.image);
        }
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
