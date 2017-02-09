package com.sem4ikt.uni.recipefinderchatbot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.yalantis.guillotine.animation.GuillotineAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mathias Pedersen on 08/02/17.
 */
public class MainActivity extends AppCompatActivity {

    // Views to bind
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.root)
    FrameLayout root;
    @BindView(R.id.content_hamburger)
    View contentHamburger;
    @BindView(R.id.frame_container)
    FrameLayout frameContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set content view
        setContentView(R.layout.activity);

        setupMenu();



    }

    private void setupMenu(){

        // Bind all views defined using butterknife
        ButterKnife.bind(this);

        if (toolbar != null) {

            setSupportActionBar(toolbar);

            try{ getSupportActionBar().setTitle(null); }
            catch (Exception e){ e.printStackTrace(); }
        }

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);

        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(250)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();

    }

    private void startFragment(){

        // Create a new Fragment to be placed in the activity layout
        ChatbotFragment firstFragment = new ChatbotFragment();

        // Add the fragment to the 'fragment_container' FrameLayout
        //getFragmentManager().beginTransaction().add(R.id.frame_container, firstFragment).commit();


    }

}
