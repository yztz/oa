package com.oasystem;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.oasystem.bean.Product;
import com.oasystem.utils.UrlUtils;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class MyBannerAdapter extends BannerAdapter<Product, MyBannerAdapter.BannerViewHolder> {
    List<Product> mData;


    public MyBannerAdapter(List<Product> mData) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mData);
        this.mData = mData;
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, Product product, int position, int size) {
        NetworkGlideImageLoader.getInstance()
                .displayImage(holder.imageView,
                        UrlUtils.getPicUrl(product.getPicName()),
                        holder.imageView);
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
