package com.example.asus.simulation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.simulation.api.Apis;
import com.example.asus.simulation.api.UserApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText edit;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = findViewById(R.id.edit_keyword);

    }

    public void btn_search(View view) {

        s = edit.getText().toString();

        EventBus.getDefault().postSticky(s);
        startActivity(new Intent(MainActivity.this,Main2Activity.class));

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
