package com.sem4ikt.uni.recipefinderchatbot.Rest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mathiaslykkepedersen on 06/03/2017.
 */

public class ApiClient implements IApiClient {

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/";

    public Retrofit getClient(){
        if(retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
//X-Mashape-Key: sZ5SigKs5xmshAE2m0byUGB3Q8AZp1VCiP8jsnU7s14kbiwWRP", "Accept: application/json
                    Request request = original.newBuilder()
                            .header("X-Mashape-Key", "sZ5SigKs5xmshAE2m0byUGB3Q8AZp1VCiP8jsnU7s14kbiwWRP")
                            .header("Accept", "application/json")
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            });
            OkHttpClient client = httpClient.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

}
