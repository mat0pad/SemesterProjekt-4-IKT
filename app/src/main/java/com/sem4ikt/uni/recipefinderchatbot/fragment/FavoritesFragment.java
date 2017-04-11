package com.sem4ikt.uni.recipefinderchatbot.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.activity.DetailRecipeActivity;
import com.sem4ikt.uni.recipefinderchatbot.adapter.FavoritesGridAdapter;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.FavoritesPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IFavoritesPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IDetailRecipeView;
import com.sem4ikt.uni.recipefinderchatbot.view.IFavoritesView;

import java.util.List;

/**
 * Created by Christian on 12-03-2017.
 */

public class FavoritesFragment extends Fragment implements IFavoritesView {

    GridView gridView;
    IFavoritesPresenter<IFavoritesView> presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (container == null) {
            return null;
        }

        presenter = new FavoritesPresenter(this);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.favorite, container, false);

        SearchView searchView = (SearchView) view.findViewById(R.id.search_bar_favor);

        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText searchEditText = (EditText) searchView.findViewById(id);
        searchEditText.setTextColor(ContextCompat.getColor(getContext(), R.color.Primary_Dark));
        searchEditText.setHintTextColor(ContextCompat.getColor(getContext(), R.color.Primary_Dark));

        gridView = (GridView) view.findViewById(R.id.favorites_gridview);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.doSearch(query);
                Log.e("textsubmit",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("Textchange",newText);
                presenter.doSearch(newText);
                return false;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                IDetailRecipeView IDR = new DetailRecipeActivity();
            }
        });


        presenter.getRecipeList();



        return view;
    }

    @Override
    public void displayList(List<RecipesModel> list) {
        FavoritesGridAdapter adapter = new FavoritesGridAdapter(getContext(), list);
        gridView.setAdapter(adapter);
    }

    @Override
    public void Update(String query) {
    }


}
