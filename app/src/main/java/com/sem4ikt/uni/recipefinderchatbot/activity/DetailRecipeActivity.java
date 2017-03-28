package com.sem4ikt.uni.recipefinderchatbot.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.adapter.IngredientsGridAdapter;
import com.sem4ikt.uni.recipefinderchatbot.adapter.SimilarGridAdapter;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.ExtendedIngredientModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.InstructionsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.SummaryModel;
import com.sem4ikt.uni.recipefinderchatbot.other.ExpandingGridView;
import com.sem4ikt.uni.recipefinderchatbot.presenter.DetailRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IDetailRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IDetailRecipeView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 13/02/2017.
 */

public class DetailRecipeActivity extends AppCompatActivity implements IDetailRecipeView, View.OnClickListener {

    IDetailRecipePresenter presenter;
    SimilarGridAdapter adapterSimilar;
    IngredientsGridAdapter adapterIngredient;

    ImageView posterImage, saveFavorite;
    TextView instructions, title, summary;
    ExpandingGridView similarGrid, ingredientsGrid;

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
        summary = (TextView) findViewById(R.id.summary_text);
        saveFavorite = (ImageView) findViewById(R.id.favorite_save);
        similarGrid = (ExpandingGridView) findViewById(R.id.similar_grid);
        ingredientsGrid = (ExpandingGridView) findViewById(R.id.ingredients_grid);

        // Similar setup
        List<RecipesModel> listRecipesModel = new ArrayList<>();
        adapterSimilar = new SimilarGridAdapter(getApplicationContext(), listRecipesModel);
        similarGrid.setAdapter(adapterSimilar);

        // Ingredients setup
        List<ExtendedIngredientModel> listExtendedIngredientModel = new ArrayList<>();
        adapterIngredient = new IngredientsGridAdapter(getApplicationContext(), listExtendedIngredientModel);
        ingredientsGrid.setAdapter(adapterIngredient);


        int viewWidth = (int) (90 * getResources().getDisplayMetrics().density);
        int numOfColumns = getResources().getDisplayMetrics().widthPixels / viewWidth;
        ingredientsGrid.setNumColumns(numOfColumns);

        // Click listener for save button
        saveFavorite.setOnClickListener(this);

        presenter.doLoadRecipe(123);
        presenter.doInstructions(123);
        presenter.doSummarize(123);
        presenter.doFindSimilar(123);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.favorite_save:
                saveFavorite.setImageDrawable(getDrawable((isSaved ? R.drawable.like : R.drawable.like_filled)));
                isSaved = !isSaved;

                // save it here!
                presenter.doSaveRecipe();
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
        for (RecipesModel item : similar)
            adapterSimilar.list.add(item);

        adapterSimilar.notifyDataSetChanged();
    }


    @Override
    public void setRecipe(RecipeModel recipe) {

        title.setText(recipe.getTitle());

        //instructions.setText(recipe.getInstructions());
        // Load big image
        Picasso.with(getApplicationContext()).load(recipe.getImage()).into(posterImage);

        for (ExtendedIngredientModel item : recipe.getExtendedIngredients())
            adapterIngredient.list.add(item);

        adapterIngredient.notifyDataSetChanged();
    }

    @Override
    public void setInstructions(List<InstructionsModel> instructions) {

        if (instructions.size() >= 1) {

            String steps = "";
            System.out.println("setInstructions");

            int size = instructions.get(0).getSteps().size();

            for (int i = 0; i < size; i++)
                steps += "• " + instructions.get(0).getSteps().get(i).getStep() + "\n" + (i != size - 1 ? "\n" : "");

            this.instructions.setText(steps);
        }
    }
}
