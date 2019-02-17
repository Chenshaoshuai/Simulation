package com.example.asus.simulation.mvp;

public interface IView<T> {
    void  onSuccess(T data);
    void  onFails(String e);
}
