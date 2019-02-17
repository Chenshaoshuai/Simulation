package com.example.asus.simulation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.asus.simulation.api.Apis;
import com.example.asus.simulation.api.UserApiService;
import com.example.asus.simulation.entiry.User;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecycleAdapter adapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EventBus.getDefault().register(this);
        recyclerView = findViewById(R.id.recyle);


    }



    @Subscribe(sticky = true)
    public void rq(String s){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apis.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        UserApiService userApiService = retrofit.create(UserApiService.class);

        HashMap<String,String> params = new HashMap<>();
        params.put("keyword",s);
        params.put("page","1");
        params.put("count","10");

        Call<User> request = userApiService.Request(Apis.KEY_URL, params);

        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                GridLayoutManager gridLayoutManager = new GridLayoutManager(Main2Activity.this,2);
                gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
                recyclerView.setLayoutManager(gridLayoutManager);
                adapter = new RecycleAdapter(Main2Activity.this);
                recyclerView.setAdapter(adapter);
                adapter.setOnClicklayout(new RecycleAdapter.OnClicklayout() {
                    @Override
                    public void onClick(int position) {
                        EventBus.getDefault().postSticky(position+"");
                        startActivity(new Intent(Main2Activity.this,Main3Activity.class));
                    }
                });
                adapter.setData(user.getResult());


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Main2Activity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
