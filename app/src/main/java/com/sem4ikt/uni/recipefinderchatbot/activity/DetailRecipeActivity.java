package com.sem4ikt.uni.recipefinderchatbot.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.adapter.IngredientsGridAdapter;
import com.sem4ikt.uni.recipefinderchatbot.adapter.SimilarGridAdapter;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.SummaryModel;
import com.sem4ikt.uni.recipefinderchatbot.other.ExpandingGridView;
import com.sem4ikt.uni.recipefinderchatbot.presenter.DetailRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.IngredientsAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.SimilarAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IDetailRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IIngredientsAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ISimilarAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IDetailRecipeView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mathiaslykkepedersen on 13/02/2017.
 */

public class DetailRecipeActivity extends AppCompatActivity implements IDetailRecipeView, View.OnClickListener {

    // Own presenter
    IDetailRecipePresenter presenter;

    // Similar adapter presenter
    ISimilarAdapterPresenter similarAdapterPresenter;

    // Ingredients adapter presenter
    IIngredientsAdapterPresenter ingredientAdapterPresenter;

    ImageView posterImage, saveFavorite;
    TextView instructions, title, summary;
    ExpandingGridView similarGrid, ingredientsGrid;

    RecipeModel recipe;

    boolean isSaved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        // Set presenter
        presenter = new DetailRecipePresenter(this);

        // Find views
        title = (TextView) findViewById(R.id.recipe_title);
        posterImage = (ImageView) findViewById(R.id.poster_imageView);
        instructions = (TextView) findViewById(R.id.instrution_text);
        summary = (TextView) findViewById(R.id.summary_text);
        saveFavorite = (ImageView) findViewById(R.id.favorite_save);
        similarGrid = (ExpandingGridView) findViewById(R.id.similar_grid);
        ingredientsGrid = (ExpandingGridView) findViewById(R.id.ingredients_grid);

        // Similar setup
        SimilarGridAdapter adapterSimilar = new SimilarGridAdapter(getApplicationContext());
        similarGrid.setAdapter(adapterSimilar);
        similarAdapterPresenter = new SimilarAdapterPresenter(adapterSimilar);

        // Ingredients setup
        IngredientsGridAdapter ingredientsGridAdapter = new IngredientsGridAdapter(getApplicationContext());
        ingredientsGrid.setAdapter(ingredientsGridAdapter);
        ingredientAdapterPresenter = new IngredientsAdapterPresenter(ingredientsGridAdapter);

        // Set num of columns manually since android defaults to 2 when using wrap_content
        int viewWidth = (int) (90 * getResources().getDisplayMetrics().density);
        int numOfColumns = getResources().getDisplayMetrics().widthPixels / viewWidth;
        ingredientsGrid.setNumColumns(numOfColumns);

        // Click listener for save button
        saveFavorite.setOnClickListener(this);

        // Set similar item click listener
        similarGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int recipeId = similarAdapterPresenter.getItemId(position);

                similarAdapterPresenter.doClick(recipeId);
            }
        });

        Bundle bundle = getIntent().getExtras();
        int id = -1;
        if (bundle != null)
            id = bundle.getInt("id");

        if (id != -1) {
            // Load data
            presenter.doLoadRecipe(id);
            presenter.doInstructions(id);
            presenter.doSummarize(id);
            presenter.doFindSimilar(id);
        } else {
            // Load data
            presenter.doLoadRecipe(123);
            presenter.doInstructions(123);
            presenter.doSummarize(123);
            presenter.doFindSimilar(123);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.favorite_save:
                saveFavorite.setImageDrawable(getDrawable((isSaved ? R.drawable.like : R.drawable.like_filled)));
                isSaved = !isSaved;
                if(isSaved)
                    presenter.doSaveRecipe(recipe);
                else
                    presenter.doDeleteRecipe(recipe);
            default:
                break;
        }
    }

    @Override
    public void setSummary(SummaryModel model) {
        summary.setText(Html.fromHtml(model.getSummary()));
    }

    @Override
    public void setSimilar(List<RecipesModel> similar) {

        // Ask presenter to load list
        similarAdapterPresenter.setList(similar);

        // Show loaded content
        presenter.doShowContent();
    }

    @Override
    public void setRecipe(RecipeModel recipe) {

        title.setText(recipe.getTitle());

        this.recipe = recipe;
        // Load big image
        Picasso.with(getApplicationContext()).load(recipe.getImage()).fit().into(posterImage);

        ingredientAdapterPresenter.addAll(recipe.getExtendedIngredients());
    }

    @Override
    public void setInstructions(String instructions) {

        this.instructions.setText(instructions);

    }

    @Override
    public void showContent(boolean shouldShow) {

        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView_detail_recipe);
        scrollView.setVisibility((shouldShow ? View.VISIBLE : View.GONE));
    }

    @Override
    public void showLoader(boolean shouldShow) {

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.detail_recipe_progress);
        progressBar.setVisibility((shouldShow ? View.VISIBLE : View.GONE));
    }
}
