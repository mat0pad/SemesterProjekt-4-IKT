package com.sem4ikt.uni.recipefinderchatbot.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.adapter.SimilarGridAdapter;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.DetailRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IDetailRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.rest.ApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.IApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.ISpoonacularAPI;
import com.sem4ikt.uni.recipefinderchatbot.view.IDetailRecipeView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mathiaslykkepedersen on 13/02/2017.
 */

public class DetailRecipeActivity extends AppCompatActivity implements IDetailRecipeView, View.OnClickListener {

    IDetailRecipePresenter presenter;
    SimilarGridAdapter adapter;

    ImageView posterImage, saveFavorite;
    TextView instructions, title;
    GridView similarGrid, ingredientsGrid;

    boolean isSaved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        // Set presenter
        presenter = new DetailRecipePresenter(this);

        title = (TextView) findViewById(R.id.recipe_title);
        posterImage = (ImageView) findViewById(R.id.poster_imageView);
        instructions = (TextView) findViewById(R.id.instrution_text);
        saveFavorite = (ImageView) findViewById(R.id.favorite_save);

        similarGrid = (GridView) findViewById(R.id.similar_grid);

        List<RecipesModel> list = new ArrayList<>();
        adapter = new SimilarGridAdapter(getApplicationContext(), list);

        similarGrid.setAdapter(adapter);

        saveFavorite.setOnClickListener(this);

        /* TEST !! */
        IApiClient client = new ApiClient();
        ISpoonacularAPI.IData apiService1 = client.getClient().create(ISpoonacularAPI.IData.class);

        Call<RecipeModel> call1 = apiService1.getRecipe(43, false);

        call1.enqueue(new Callback<RecipeModel>() {
            @Override
            public void onResponse(Call<RecipeModel> call, Response<RecipeModel> response) {

                if (response.code() == 200) {

                    RecipeModel model = response.body();

                    instructions.setText(model.getInstructions());
                    title.setText(model.getTitle());
                    System.out.println(model.getSourceUrl() + model.getImage());
                    Picasso.with(getApplicationContext()).load(model.getImage()).into(posterImage);

                }
            }

            @Override
            public void onFailure(Call<RecipeModel> call, Throwable t) {
            }
        });

        ISpoonacularAPI.ISearch apiService2 = client.getClient().create(ISpoonacularAPI.ISearch.class);

        Call<List<RecipesModel>> call2 = apiService2.findSimilarRecipes(43);

        call2.enqueue(new Callback<List<RecipesModel>>() {
            @Override
            public void onResponse(Call<List<RecipesModel>> call, Response<List<RecipesModel>> response) {

                if (response.code() == 200) {

                    List<RecipesModel> model = response.body();

                    for (RecipesModel item : model)
                        adapter.list.add(item);

                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<RecipesModel>> call, Throwable t) {
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.favorite_save:
                saveFavorite.setImageDrawable(getDrawable((isSaved ? R.drawable.like : R.drawable.like_filled)));
                isSaved = !isSaved;
            default:
                break;
        }
    }




}
