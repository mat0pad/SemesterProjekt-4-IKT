package com.sem4ikt.uni.recipefinderchatbot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ListDataContainer;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ListDataModel;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ListIngredientAdapter;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ListNutrientAdapter;
import com.sem4ikt.uni.recipefinderchatbot.adapter.ListRecipeAdapter;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.ListAdapterPresenter;

import java.util.Objects;

/**
 * Created by henriknielsen on 29/03/2017.
 */

public class ListRecipeActivity extends AppCompatActivity /*implements ILIstRecipeView opdater override af metode hvis bruges*/ {
    ListView listView;

    private ListAdapterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);




        final ListDataContainer dataForPresenter = getIntent().getParcelableExtra("com.sem4ikt.uni.recipefinderchatbot.fragment.ChatbotFragment.ListOfRecipesModels");

        String type = dataForPresenter.getType();

        if (Objects.equals(type, ListDataModel.ListDataType.RECIPE.toString()))
        {
            ListRecipeAdapter adapter = new ListRecipeAdapter(this);
            presenter = new ListAdapterPresenter(adapter);
            listView.setAdapter(adapter);
        }
        else if (Objects.equals(type, ListDataModel.ListDataType.INGREDIENT.toString()))
        {
            ListIngredientAdapter adapter = new ListIngredientAdapter(this);
            presenter = new ListAdapterPresenter(adapter);
            listView.setAdapter(adapter);
        }
        else if (Objects.equals(type, ListDataModel.ListDataType.NUTRIENT.toString()))
        {
            ListNutrientAdapter adapter = new ListNutrientAdapter(this);
            presenter = new ListAdapterPresenter(adapter);
            listView.setAdapter(adapter);
        }

        setListData(dataForPresenter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item value
                int modelId = ((RecipesModel) listView.getItemAtPosition(position)).getId();

                final Intent intent = new Intent(ListRecipeActivity.this.getApplication(), DetailRecipeActivity.class);
                intent.putExtra("id", modelId);

                startActivity(intent);

            }

        });
    }

    //@Override
    private void setListData(ListDataContainer container) {
        presenter.setContainer(container);
    }
}
