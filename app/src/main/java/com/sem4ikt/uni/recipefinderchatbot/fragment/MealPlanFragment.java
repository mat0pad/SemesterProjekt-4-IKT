package com.sem4ikt.uni.recipefinderchatbot.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sem4ikt.uni.recipefinderchatbot.R;
import com.sem4ikt.uni.recipefinderchatbot.activity.DetailRecipeActivity;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.MealPlanDayModel;
import com.sem4ikt.uni.recipefinderchatbot.rest.ApiClient;
import com.sem4ikt.uni.recipefinderchatbot.rest.ISpoonacularAPI;
import com.sem4ikt.uni.recipefinderchatbot.view.IMealPlanView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Christian on 12-03-2017.
 */

public class MealPlanFragment extends Fragment implements IMealPlanView {

    ImageView dinner;
    ImageView breakfast;
    ImageView lunch;
    MealPlanDayModel dayModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (container == null) {
            return null;
        }

        final View view = inflater.inflate(R.layout.mealplan, container, false);

        dinner = (ImageView) view.findViewById(R.id.dinner);
        breakfast = (ImageView) view.findViewById(R.id.breakfast);
        lunch = (ImageView) view.findViewById(R.id.lunch);



        ApiClient client = new ApiClient();
        ISpoonacularAPI.ICompute apiService = client.getClient().create(ISpoonacularAPI.ICompute.class);

        Call<MealPlanDayModel>  call = apiService.getDayMealPlan("vegan", 1000, "curry");


        call.enqueue(new Callback<MealPlanDayModel>() {
            @Override
            public void onResponse(Call<MealPlanDayModel> call, Response<MealPlanDayModel> response) {

                Log.i("TESTLIST", Integer.toString(response.code()));

                if(response.code() == 200) {

                    final MealPlanDayModel model = response.body();
                    dayModel= response.body();

                    Handler mainHandler = new Handler(Looper.getMainLooper());

                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            // Pass the data
                            String image =  model.getRecipeModels().get(0).getImage();

                            {
                                String BASE_URL = "https://spoonacular.com/recipeImages/";
                                String imageUrl;

                                if (image.contains("https"))
                                    imageUrl = image;
                                else
                                    imageUrl = BASE_URL + image;

                                Picasso.with(getContext()).load(imageUrl).fit().into(breakfast);
                            }
                            image =  model.getRecipeModels().get(1).getImage();

                            {
                                String BASE_URL = "https://spoonacular.com/recipeImages/";
                                String imageUrl;

                                if (image.contains("https"))
                                    imageUrl = image;
                                else
                                    imageUrl = BASE_URL + image;

                                Picasso.with(getContext()).load(imageUrl).fit().into(lunch);
                            }
                            image =  model.getRecipeModels().get(2).getImage();

                            {
                                String BASE_URL = "https://spoonacular.com/recipeImages/";
                                String imageUrl;

                                if (image.contains("https"))
                                    imageUrl = image;
                                else
                                    imageUrl = BASE_URL + image;

                                Picasso.with(getContext()).load(imageUrl).fit().into(dinner);
                            }

                        }
                    };
                    mainHandler.post(myRunnable);

                }
                else{
                    // Something went wrong
                    Log.e("TESTLIST", response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<MealPlanDayModel> call, Throwable t) {

            }
        });


        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
               id=dayModel.getRecipeModels().get(0).getId();

                final Intent intent = new Intent(MealPlanFragment.this.getActivity().getApplication(), DetailRecipeActivity.class);
                intent.putExtra("id", id);

                startActivity(intent);
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                id=dayModel.getRecipeModels().get(1).getId();

                final Intent intent=new Intent(MealPlanFragment.this.getActivity().getApplication(),DetailRecipeActivity.class);
                intent.putExtra("id",id);

                startActivity(intent);

            }
        });

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                id=dayModel.getRecipeModels().get(2).getId();

                final Intent intent=new Intent(MealPlanFragment.this.getActivity().getApplication(),DetailRecipeActivity.class);
                intent.putExtra("id",id);

                startActivity(intent);

            }
        });





        // Inflate the layout for this fragment


        return view;
    }
}
