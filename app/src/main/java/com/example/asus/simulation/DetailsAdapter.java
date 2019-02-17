package com.example.asus.simulation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asus.simulation.entiry.Detail;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;


public class DetailsAdapter extends PagerAdapter {
  private Detail.ResultBean mDatas;
  private Context context;



    public DetailsAdapter(Detail.ResultBean mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //ImageView imageView = new ImageView(container.getContext());

        String images = mDatas.getPicture();
        Pattern pattern = compile("\\|");
        String[] img = pattern.split(images);


        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(container.getContext());
        simpleDraweeView.setImageURI(img[position]);
        container.addView(simpleDraweeView);

        return simpleDraweeView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
