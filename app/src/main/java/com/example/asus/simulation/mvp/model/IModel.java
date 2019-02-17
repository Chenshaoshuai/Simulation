package com.example.asus.simulation.mvp.model;

import java.util.Map;

public interface IModel {
    void onrequest(String url, Map<String,String> map);
}
