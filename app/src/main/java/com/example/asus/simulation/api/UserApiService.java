package com.example.asus.simulation.api;

import com.example.asus.simulation.entiry.Detail;
import com.example.asus.simulation.entiry.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface UserApiService {
    @GET
    Call<User> Request(@Url String url, @QueryMap HashMap<String,String> params);
    @GET
    Call<Detail> Deta(@Url String url, @QueryMap HashMap<String,String> params);
}
