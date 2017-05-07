package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.model.SearchModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.view.IFavoritesGridAdapterView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.booleanThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by mathiaslykkepedersen on 04/05/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class FavoritesGridAdapterPresenterTest {

    @Mock
    IFavoritesGridAdapterView view;

    @Mock
    SearchModel searchModel;

    private FavoritesGridAdapterPresenter presenter;


    @Before
    public void setup() throws Exception {

        presenter = new FavoritesGridAdapterPresenter(view);
        presenter = new FavoritesGridAdapterPresenter(view,searchModel);
    }

    @Test
    public void clearViewOnDestroy() {
        presenter.clearView();

        Assert.assertEquals(presenter.getView(), null);
    }

    @Test
    public void setView() {
        presenter.clearView();
        presenter.setView(view);

        Assert.assertEquals(presenter.getView(), view);
    }

    @Test
    public void getListTestViewCall()
    {
        presenter.getList();
        verify(view,times(1)).getList();
    }

    @Test
    public void addRecipeTestViewaddRecipeCall()
    {
        RecipeModel recipe = new RecipeModel();
        presenter.addRecipe(recipe);
        verify(view,times(1)).addRecipe(recipe);
    }

    @Test
    public void addRecipeTestVievNotifyUpdateCall()
    {
        RecipeModel recipe = new RecipeModel();
        presenter.addRecipe(recipe);
        verify(view,times(1)).notifyUpdate();
    }

    @Test
    public void doSearchViewCallSetListTest()
    {
        presenter.doSearch("test");

        List<RecipeModel> list = new ArrayList<>();
        when(searchModel.searchSingleThread(any(List.class),anyString())).thenReturn(list);

        verify(view,times(1)).setList(list);
    }

    @Test
    public void doSearchViewCallSearchModelTest()
    {
        presenter.doSearch("test");

        verify(searchModel,times(1)).searchSingleThread(any(List.class),anyString());
    }

    @Test
    public void setListViewSetListTest()
    {
        List<RecipeModel> list = new ArrayList<>();

        presenter.setList(list);

        verify(view,times(1)).setList(list);
    }

    @Test
    public void setListVievSetFullListTest()
    {
        List<RecipeModel> list = new ArrayList<>();

        presenter.setList(list);

        verify(view,times(1)).setFullList(list);
    }

    @Test
    public void setListViewNotifyUpdateTest()
    {
        List<RecipeModel> list = new ArrayList<>();

        presenter.setList(list);

        verify(view,times(1)).notifyUpdate();
    }

    @Test
    public void onClickViewGetItemTest()
    {
        int position = 0;

        presenter.onClick(position);

        verify(view,times(1)).getItem(position);
    }

    @Test
    public void onClickVievShowRecipeTest()
    {
        int position = 0;

        presenter.onClick(position);

        verify(view,times(1)).showRecipe(any(RecipeModel.class));
    }

    @Test
    public void getItemViewCallTest()
    {
        int position = 0;
        presenter.getItem(position);

        verify(view,times(1)).getItem(position);
    }

    @Test
    public void deleteRecipeViewDeleteRecipeCallTest()
    {
        RecipeModel recipe = new RecipeModel();
        presenter.deleteRecipe(recipe);

        verify(view,times(1)).deleteRecipe(recipe);
    }

    @Test
    public void deleteRecipeViewNotifyUpdateCallTest()
    {
        RecipeModel recipe = new RecipeModel();
        presenter.deleteRecipe(recipe);

        verify(view,times(1)).notifyUpdate();
    }

    @Test
    public void isDeletingViewCall()
    {
        boolean isdeleting = false;

        presenter.isDeleting(isdeleting);

        verify(view,times(1)).setDeleting(isdeleting);

       verify(view,times(1)).notifyUpdate();
    }

    @Test
    public void deleteRecipePostionViewCallTest()
    {
        int position = 0;

        presenter.deleteRecipe(position);

        verify(view,times(1)).getItem(position);
        verify(view,times(1)).deleteRecipe(any(RecipeModel.class));
        verify(view,times(1)).notifyUpdate();
    }


}
