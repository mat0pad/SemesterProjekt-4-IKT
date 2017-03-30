package com.sem4ikt.uni.recipefinderchatbot.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.adapter.FavoritesGridAdapter;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IFavoritesView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 12-03-2017.
 */

public class FavoritesFragment extends Fragment implements IFavoritesView {

    GridView gridView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (container == null) {
            return null;
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.favorite, container, false);

        SearchView searchView = (SearchView) view.findViewById(R.id.search_bar_favor);

        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText searchEditText = (EditText) searchView.findViewById(id);
        searchEditText.setTextColor(ContextCompat.getColor(getContext(), R.color.Primary_Dark));
        searchEditText.setHintTextColor(ContextCompat.getColor(getContext(), R.color.Primary_Dark));


        gridView = (GridView) view.findViewById(R.id.favorites_gridview);

        List<RecipesModel> list = new ArrayList<>();

        for (int i = 10; i != 0; i--){

            RecipesModel test = new RecipesModel();
            test.setTitle("test" + i);

            list.add(test);
        }

        FavoritesGridAdapter adapter = new FavoritesGridAdapter(getContext(), list);
        gridView.setAdapter(adapter);

        return view;
    }
}
