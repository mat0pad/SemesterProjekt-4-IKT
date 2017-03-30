package com.sem4ikt.uni.recipefinderchatbot.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipesModel;
import com.sem4ikt.uni.recipefinderchatbot.presenter.ListRecipeAdapterPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.ILIstRecipeView;

import java.util.List;

/**
 * Created by henriknielsen on 29/03/2017.
 */

public class ListRecipeActivity extends AppCompatActivity implements ILIstRecipeView {
    ListView listView;

    private com.sem4ikt.uni.recipefinderchatbot.adapter.ListRecipeAdapter adapter = new com.sem4ikt.uni.recipefinderchatbot.adapter.ListRecipeAdapter(this);
    private ListRecipeAdapterPresenter presenter = new ListRecipeAdapterPresenter(adapter);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });
    }

    @Override
    public void setRecipeList(List<RecipesModel> models) {
        presenter.setRecipeList(models);
    }
}
