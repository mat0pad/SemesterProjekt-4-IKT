package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.adapter.FavoritesGridAdapter;
import com.sem4ikt.uni.recipefinderchatbot.model.SearchModel;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ISearchModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IFavoritesGridAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IFavoritesGridAdapterView;

import java.util.List;

/**
 * Created by anton on 06-04-2017.
 */

public class FavoritesGridAdapterPresenter extends BasePresenter<IFavoritesGridAdapterView> implements IFavoritesGridAdapterPresenter<IFavoritesGridAdapterView> {

    private ISearchModel search;

    public FavoritesGridAdapterPresenter(IFavoritesGridAdapterView view) {
        super(view);
        search = new SearchModel();
    }

    @Override
    public List<RecipeModel> getList() {
        return view.getList();
    }

    @Override
    public void addRecipe(RecipeModel recipe) {

        view.addRecipe(recipe);
        view.notifyUpdate();
    }

    @Override
    public void update(List<RecipeModel> list) {
        view.notifyUpdate();
    }

    @Override
    public void doSearch(String query) {
        List<RecipeModel> list = getList();
        view.setList(search.searchSingleThread(list,query));
        view.notifyUpdate();
    }

    @Override
    public void setList(List<RecipeModel> list) {
        view.setList(list);
        view.setFullList(list);
        view.notifyUpdate();
    }

    @Override
    public void onClick(int position) {
        RecipeModel recipeModel = view.getItem(position);
        view.showRecipe(recipeModel);
    }
    @Override
    public RecipeModel getItem(int position) {
        return view.getItem(position);
    }

    @Override
    public void deleteRecipe(RecipeModel recipe) {
        view.deleteRecipe(recipe);
        view.notifyUpdate();
    }

    @Override
    public void isDeleting(boolean isdeleting) {
        view.setDeleting(isdeleting);
        view.notifyUpdate();
    }

    @Override
    public void deleteRecipe(int position) {
        RecipeModel recipe = view.getItem(position);
        view.deleteRecipe(recipe);
        view.notifyUpdate();
    }


}
