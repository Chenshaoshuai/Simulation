package com.example.asus.simulation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asus.simulation.api.Apis;
import com.example.asus.simulation.api.UserApiService;
import com.example.asus.simulation.entiry.Detail;
import com.example.asus.simulation.entiry.User;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main3Activity extends AppCompatActivity {
    private Banner banner;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EventBus.getDefault().register(this);
         viewPager = findViewById(R.id.main_pager);
//        Intent intent = getIntent();
//        count = intent.getStringExtra("commodityId");


    }
    @Subscribe( sticky = true)
    public void cid(String id){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apis.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        UserApiService userApiService = retrofit.create(UserApiService.class);
        HashMap<String,String> params = new HashMap<>();
        params.put("commodityId",id);

        Call<Detail> deta = userApiService.Deta(Apis.DETAIL_URL, params);

        deta.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                Detail detail = response.body();

                Detail.ResultBean result = detail.getResult();

                DetailsAdapter adapter = new DetailsAdapter(result,Main3Activity.this);
                viewPager.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {
                 Toast.makeText(Main3Activity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);


    }
}
