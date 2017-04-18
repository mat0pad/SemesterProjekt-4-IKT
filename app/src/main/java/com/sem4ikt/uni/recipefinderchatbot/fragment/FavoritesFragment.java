package com.sem4ikt.uni.recipefinderchatbot.fragment;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.FavoritesGridAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.FavoritesPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IFavoritesGridAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IFavoritesPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IFavoritesGridAdapterView;
import com.sem4ikt.uni.recipefinderchatbot.view.IFavoritesView;

import java.util.Date;
import java.util.List;

/**
 * Created by Christian on 12-03-2017.
 */

public class FavoritesFragment extends Fragment implements IFavoritesView {

    GridView gridView;
    IFavoritesPresenter<IFavoritesView> presenter;
    IFavoritesGridAdapterPresenter<IFavoritesGridAdapterView> gridPresenter;
    boolean isdeleting = false;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.FloatingActionButton);

        gridView = (GridView) view.findViewById(R.id.favorites_gridview);

        FavoritesGridAdapter adapter = new FavoritesGridAdapter(getContext());
        gridView.setAdapter(adapter);

        gridPresenter = new FavoritesGridAdapterPresenter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                gridPresenter.doSearch(newText);
                return false;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                if(!isdeleting)
                    gridPresenter.onClick(position);
                else {
                    presenter.deleteRecipe(gridPresenter.getItem(position));
                    gridPresenter.deleteRecipe(position);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isdeleting = !isdeleting;
                if(isdeleting)
                    fab.setImageResource(R.drawable.no_edit);
                else
                    fab.setImageResource(R.drawable.edit);

                gridPresenter.isDeleting(isdeleting);
            }
        });
        return view;
    }


    @Override
    public void update(List<RecipeModel> list) {
        gridPresenter.update(list);
    }

    @Override
    public void setList(List<RecipeModel> list) {
        gridPresenter.setList(list);
    }

    @Override
    public void deleteRecipe(RecipeModel recipe) {
        gridPresenter.deleteRecipe(recipe);
    }

    @Override
    public void addRecipe(RecipeModel recipe) {
        gridPresenter.addRecipe(recipe);
    }


    @Override
    public void onResume()
    {
        super.onResume();
        Log.e("Welcome","back");
        presenter.checkForUpdates();

    }


}
