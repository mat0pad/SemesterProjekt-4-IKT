package com.sem4ikt.uni.recipefinderchatbot.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.fragment.ChatbotFragment;
import com.sem4ikt.uni.recipefinderchatbot.fragment.FavoritesFragment;
import com.sem4ikt.uni.recipefinderchatbot.fragment.MealPlanFragment;
import com.sem4ikt.uni.recipefinderchatbot.fragment.SettingsFragment;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.AnswerModel;
import com.sem4ikt.uni.recipefinderchatbot.other.CanaroTextView;
import com.sem4ikt.uni.recipefinderchatbot.presenter.MainPresenter;
import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IMainPresenter;
import com.sem4ikt.uni.recipefinderchatbot.rest.ApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.IApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.ISpoonacularAPI;
import com.sem4ikt.uni.recipefinderchatbot.view.IMainView;
import com.yalantis.guillotine.animation.GuillotineAnimation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Mathias Pedersen on 08/02/17.
 */
public class MainActivity extends AppCompatActivity implements IMainView , View.OnClickListener {

    private Toolbar toolbar;
    private FrameLayout root;
    private View contentHamburger;
    FrameLayout frameContainer;
    private GuillotineAnimation builder;
    CanaroTextView mealPlan, chatbot, settings, favorites, menuTitle;


    private IMainPresenter mainPresenter;

    private enum FragmentMenu{
        CHATBOT,
        MEAL_PLAN,
        FAVORITES,
        SETTINGS
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set content view
        setContentView(R.layout.activity);

        // Bind views
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        frameContainer = (FrameLayout) findViewById(R.id.frame_container);
        contentHamburger = findViewById(R.id.content_hamburger);
        root = (FrameLayout) findViewById(R.id.root);

        mainPresenter = new MainPresenter(this);
        mainPresenter.setupMenu();
        mainPresenter.displayFragment(FragmentMenu.CHATBOT.ordinal());

    }

    @Override
    public void onClick(View v){

        switch (v.getId())
        {
            case R.id.meal_plan:
                showFragment(FragmentMenu.MEAL_PLAN.ordinal());
                menuTitle.setText(R.string.meal_plan);
                builder.close();
                break;
            case R.id.chatbot:
                showFragment(FragmentMenu.CHATBOT.ordinal());
                menuTitle.setText(R.string.recipe_bot);
                builder.close();
                break;
            case R.id.settings:
                showFragment(FragmentMenu.SETTINGS.ordinal());
                menuTitle.setText(R.string.settings);
                builder.close();
                break;
            case R.id.favorites:
                showFragment(FragmentMenu.FAVORITES.ordinal());
                menuTitle.setText(R.string.favorites);
                builder.close();
                break;

            default:
                break;
        }

    }

    public void showFragment(int frag){

        // Add the fragment to the frameLayout
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Create a new Fragment to be placed in the activity layout
        switch (FragmentMenu.values()[frag])
        {
            case CHATBOT:
                transaction.replace(R.id.frame_container, new ChatbotFragment());
                break;
            case SETTINGS:
                transaction.replace(R.id.frame_container, new SettingsFragment());
                break;
            case MEAL_PLAN:
                transaction.replace(R.id.frame_container, new MealPlanFragment());
                break;
            case FAVORITES:
                transaction.replace(R.id.frame_container, new FavoritesFragment());
                break;
        }

        // Commit transaction & not add to back stack
        transaction.addToBackStack(null).commit();

    }

    public void setup(){

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }


        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);

        mealPlan = (CanaroTextView) findViewById(R.id.meal_plan);
        chatbot = (CanaroTextView) findViewById(R.id.chatbot);
        settings = (CanaroTextView) findViewById(R.id.settings);
        favorites = (CanaroTextView) findViewById(R.id.favorites);
        menuTitle = (CanaroTextView) findViewById(R.id.menu_title);

        // Set onClick listeners
        mealPlan.setOnClickListener(this);
        chatbot.setOnClickListener(this);
        settings.setOnClickListener(this);
        favorites.setOnClickListener(this);

        builder = new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(250)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();
    }

    public void onDestroy(){
        super.onDestroy();

        mainPresenter.clearView();
    }


    public void testAPI()
    {
        IApiClient client = new ApiClient();

        ISpoonacularAPI.ICompute apiService = client.getClient().create(ISpoonacularAPI.ICompute.class);

        Call<AnswerModel> call = apiService.getQuickAnswer("How much vitamin c is in 2 apples?");

        call.enqueue(new Callback<AnswerModel>() {
            @Override
            public void onResponse(Call<AnswerModel> call, Response<AnswerModel> response) {

                Log.i("TAG", Thread.currentThread().getName());

                int statusCode = response.code();

                if(statusCode == 200){

                    AnswerModel model = response.body();
                    System.out.println(model.getAnswer());
                }
            }

            @Override
            public void onFailure(Call<AnswerModel> call, Throwable t) {

            }
        });




    }


}


