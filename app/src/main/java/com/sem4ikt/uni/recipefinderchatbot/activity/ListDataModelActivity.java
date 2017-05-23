package com.sem4ikt.uni.recipefinderchatbot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.adapter.IngredientListAdapter;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ListDataContainer;
import com.sem4ikt.uni.recipefinderchatbot.adapter.NutrientListAdapter;
import com.sem4ikt.uni.recipefinderchatbot.adapter.RecipeListAdapter;
import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ListDataModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.IngredientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.NutrientsModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.ListAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IListRecipePresenter;

import java.util.Objects;

/**
 * Created by henriknielsen on 29/03/2017.
 */

public class ListDataModelActivity extends AppCompatActivity /*implements ILIstRecipeView opdater override af metode hvis bruges*/ {
    ListView listView;

    private IListRecipePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        final ListDataContainer dataForPresenter = getIntent().getParcelableExtra("listOfRecipesModels");

        if (dataForPresenter != null) {
            final String type = dataForPresenter.getType();

            if (Objects.equals(type, ListDataModel.ListDataType.RECIPE.toString())) {
                RecipeListAdapter adapter = new RecipeListAdapter(this);
                presenter = new ListAdapterPresenter(adapter);
                listView.setAdapter(adapter);
            } else if (Objects.equals(type, ListDataModel.ListDataType.INGREDIENT.toString())) {
                IngredientListAdapter adapter = new IngredientListAdapter(this);
                presenter = new ListAdapterPresenter(adapter);
                listView.setAdapter(adapter);
            } else if (Objects.equals(type, ListDataModel.ListDataType.NUTRIENT.toString())) {
                NutrientListAdapter adapter = new NutrientListAdapter(this);
                presenter = new ListAdapterPresenter(adapter);
                listView.setAdapter(adapter);
            }

            setListData(dataForPresenter);

            // ListView Item Click Listener
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    int modelId = 0;

                    // ListView Clicked item value
                    if (Objects.equals(type, ListDataModel.ListDataType.RECIPE.toString())) {
                        modelId = ((RecipesModel) listView.getItemAtPosition(position)).getId();
                    } else if (Objects.equals(type, ListDataModel.ListDataType.INGREDIENT.toString())) {
                        modelId = ((IngredientsModel) listView.getItemAtPosition(position)).getId();
                    } else if (Objects.equals(type, ListDataModel.ListDataType.NUTRIENT.toString())) {
                        modelId = ((NutrientsModel) listView.getItemAtPosition(position)).getId();
                    }

                    final Intent intent = new Intent(ListDataModelActivity.this.getApplication(), DetailRecipeActivity.class);
                    intent.putExtra("id", modelId);

                    startActivity(intent);

                }

            });
        }
    }

    //@Override
    private void setListData(ListDataContainer container) {
        presenter.setContainer(container);
    }
}
