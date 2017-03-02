package com.sem4ikt.uni.recipefinderchatbot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.sem4ikt.uni.recipefinderchatbot.Presenter.IMainPresenter;
import com.sem4ikt.uni.recipefinderchatbot.Presenter.MainPresenter;
import com.sem4ikt.uni.recipefinderchatbot.View.IMainView;
import com.yalantis.guillotine.animation.GuillotineAnimation;


/**
 * Created by Mathias Pedersen on 08/02/17.
 */
public class MainActivity extends AppCompatActivity implements IMainView , View.OnClickListener {

    Toolbar toolbar;
    FrameLayout root;
    View contentHamburger;
    FrameLayout frameContainer;
    GuillotineAnimation builder;
    CanaroTextView mealPlan, chatbot, settings, favorites;

    IMainPresenter mainPresenter;

    public enum FragmentMenu{

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
                // do something
                builder.close();
                break;
            case R.id.chatbot:
                // do something
                builder.close();
                break;
            case R.id.settings:
                // do something
                builder.close();
                break;
            case R.id.favorites:
                // do something
                Intent intent = new Intent(this, DetailRecipeActivity.class);
                startActivity(intent);
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
                break;
            case MEAL_PLAN:
                break;
            case FAVORITES:
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


}
