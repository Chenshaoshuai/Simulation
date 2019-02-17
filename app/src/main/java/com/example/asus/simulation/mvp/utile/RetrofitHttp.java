package com.example.asus.simulation.mvp.utile;

import com.example.asus.simulation.api.Apis;
import com.example.asus.simulation.api.UserApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHttp {

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .build();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Apis.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();

    UserApiService userApiService = retrofit.create(UserApiService.class);
}
