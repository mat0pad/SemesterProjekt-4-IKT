package com.sem4ikt.uni.recipefinderchatbot.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.adapter.EquipmentsGridAdapter;
import com.sem4ikt.uni.recipefinderchatbot.adapter.IngredientsGridAdapter;
import com.sem4ikt.uni.recipefinderchatbot.adapter.SimilarGridAdapter;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.EquipmentModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.SummaryModel;
import com.sem4ikt.uni.recipefinderchatbot.other.ExpandingGridView;
import com.sem4ikt.uni.recipefinderchatbot.presenter.DetailRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.EquipmentsAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.IngredientsAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.SimilarAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IDetailRecipePresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IEquipmentsAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IIngredientsAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.ISimilarAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IDetailRecipeView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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

    // Equipment adapter presenter
    IEquipmentsAdapterPresenter equipmentAdapterPresenter;

    ImageView posterImage, saveFavorite;
    TextView instructions, title, summary;
    ExpandingGridView similarGrid, ingredientsGrid, equipmentGrid;

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
        equipmentGrid = (ExpandingGridView) findViewById(R.id.tools_grid);

        // Similar grid setup
        SimilarGridAdapter adapterSimilar = new SimilarGridAdapter(getApplicationContext());
        similarGrid.setAdapter(adapterSimilar);
        similarAdapterPresenter = new SimilarAdapterPresenter(adapterSimilar);

        // Ingredients grid setup
        IngredientsGridAdapter ingredientsGridAdapter = new IngredientsGridAdapter(getApplicationContext());
        ingredientsGrid.setAdapter(ingredientsGridAdapter);
        ingredientAdapterPresenter = new IngredientsAdapterPresenter(ingredientsGridAdapter);

        // Equipment grid setup
        EquipmentsGridAdapter equipmentGridAdapter = new EquipmentsGridAdapter(getApplicationContext());
        equipmentGrid.setAdapter(equipmentGridAdapter);
        equipmentAdapterPresenter = new EquipmentsAdapterPresenter(equipmentGridAdapter);

        // Set num of columns manually since android defaults to 2 when using wrap_content
        int viewWidth = (int) (90 * getResources().getDisplayMetrics().density);
        int numOfColumns = getResources().getDisplayMetrics().widthPixels / viewWidth;
        ingredientsGrid.setNumColumns(numOfColumns);
        equipmentGrid.setNumColumns(numOfColumns);

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

        RecipeModel recipe = (RecipeModel)getIntent().getSerializableExtra("recipe");
        if(recipe != null) {
            Log.e("Recipe","found");
            isSaved = true;
            id = recipe.getId();
            saveFavorite.setImageDrawable(getDrawable(R.drawable.like_filled));
        }

        if (id != -1) {
            // Load data
            if(recipe != null)
                setRecipe(recipe);
            else
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
                if(recipe == null)
                    Log.e("recipe","Doesn't exist");
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

        presenter.doSetRecipeIconType(recipe);

        this.recipe = recipe;
        // Load big image
        Picasso.with(getApplicationContext()).load(recipe.getImage()).fit().into(posterImage);

        // Set ingredients
        ingredientAdapterPresenter.addAll(recipe.getExtendedIngredients());
    }

    @Override
    public void setInstructions(String instructions) {

        this.instructions.setText(instructions);

    }

    @Override
    public void setEquipments(List<List<EquipmentModel>> equipments) {

        List<EquipmentModel> list = new ArrayList<>();

        for (List<EquipmentModel> item : equipments)
            for (EquipmentModel i : item)
                list.add(i);

        // Hide equipment layout if null else show it
        if (list.size() == 0) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.equipment_relative);
            layout.setVisibility(View.GONE);
        } else
            equipmentAdapterPresenter.addAll(list);
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

    @Override
    public void setRecipeTypeIcon(ICON_TYPE type, boolean isVisible) {

        TextView textView = (TextView) findViewById(R.id.recipe_type_text);
        ImageView imageView = (ImageView) findViewById(R.id.recipe_type_image);

        int imageResID = 0;
        int textId = 0;

        switch (type) {

            case WHOLE30:
                textId = R.string.whole30;
                imageResID = R.drawable.whole30;
                break;

            case KETOGENIC:
                textId = R.string.ketogenic;
                imageResID = R.drawable.ketogenic;
                break;

            case WEIGHT_WATCH:
                textId = R.string.weight_watch;
                imageResID = R.drawable.weight_watch;
                break;

            case ORGANIC:
                textId = R.string.organic;
                imageResID = R.drawable.organic;
                break;

            case PALEO:
                textId = R.string.paleo;
                imageResID = R.drawable.paleo;
                break;

            case VEGAN:
                textId = R.string.vegan;
                imageResID = R.drawable.vegan;
                break;

            case VEGETARIAN:
                textId = R.string.vegetarian;
                imageResID = R.drawable.vegetarian;
                break;

            case DAIRY_FREE:
                textId = R.string.dairy_free;
                imageResID = R.drawable.dairy_free;
                break;

            case GLUTEN_FREE:
                textId = R.string.gluten_free;
                imageResID = R.drawable.gluten_free;
                break;

            default:
                break;
        }

        // Set text & image
        if (imageResID != 0)
            imageView.setImageDrawable(getDrawable(imageResID));
        if (textId != 0)
            textView.setText(getString(textId));

        // Set visibility
        imageView.setVisibility((isVisible ? View.VISIBLE : View.GONE));
        textView.setVisibility((isVisible ? View.VISIBLE : View.GONE));
    }

    @Override
    public void showRecipeTime(String time, boolean shouldShow) {

        // Set image
        ImageView imageView = (ImageView) findViewById(R.id.recipe_time_image);
        imageView.setImageDrawable(getDrawable(R.drawable.time));

        // Set text
        TextView textView = (TextView) findViewById(R.id.recipe_time_text);
        textView.setText(time);

        // Set visibility
        imageView.setVisibility((shouldShow ? View.VISIBLE : View.GONE));
        textView.setVisibility((shouldShow ? View.VISIBLE : View.GONE));
    }

    @Override
    public void showRecipePriceOrPopular(String text, boolean isPrice, boolean shouldShow) {

        // Set text
        TextView textView = (TextView) findViewById(R.id.recipe_price_text);
        textView.setText(text);

        ImageView imageView = (ImageView) findViewById(R.id.recipe_price_image);

        if (isPrice) {
            imageView.setImageDrawable(getDrawable(R.drawable.price));
        } else {
            imageView.setImageDrawable(getDrawable(R.drawable.popular));
        }

        // Set visibility
        imageView.setVisibility((shouldShow ? View.VISIBLE : View.GONE));
        textView.setVisibility((shouldShow ? View.VISIBLE : View.GONE));
    }

    public enum ICON_TYPE {
        GLUTEN_FREE,
        DAIRY_FREE,
        KETOGENIC,
        ORGANIC,
        PALEO,
        VEGAN,
        VEGETARIAN,
        WHOLE30,
        WEIGHT_WATCH,
        NONE
    }


}
