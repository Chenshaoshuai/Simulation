package com.example.asus.simulation;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.simulation.entiry.User;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
 private Context mContext;
 private List<User.ResultBean> mData;

    public RecycleAdapter(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(mData.get(i).getCommodityName());
        String images = mData.get(i).getMasterPic();
        viewHolder.image.setImageURI(Uri.parse(images));

        viewHolder.cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int commodityId = mData.get(i).getCommodityId();
                onClicklayout.onClick(commodityId);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<User.ResultBean> result) {
        this.mData=result;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.image)
        SimpleDraweeView image;
        @BindView(R.id.cons)
        ConstraintLayout cons;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
    OnClicklayout onClicklayout;

    public void setOnClicklayout(OnClicklayout onClicklayout) {
        this.onClicklayout = onClicklayout;
    }

    public interface OnClicklayout{
        void onClick(int position);
    }
}
