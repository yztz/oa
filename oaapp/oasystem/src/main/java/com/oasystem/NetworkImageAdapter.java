package com.oasystem;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.oasystem.bean.Product;
import com.oasystem.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

public class NetworkImageAdapter extends RecyclerView.Adapter<NetworkImageAdapter.CustomViewHolder> {

    private List<Product> mData;//网络图片路径集合

    private LayoutInflater mInflater; // 布局资源解析器

    private Context mContext;

    private ProductListener listener;

    public NetworkImageAdapter(Context mContext, List<Product> data, ProductListener clickListener) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        mData = data;
        this.listener = clickListener;
        //        setImages(data);
    }

//    public void setImages(List<Product> data) {
//        mData = new ArrayList<>(data);
//        notifyDataSetChanged();
//    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CustomViewHolder viewHolder = new CustomViewHolder(mInflater.inflate(R.layout.list_item_image, parent, false), listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //自定义的viewholder
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView prodName;
        private ProductListener listener;

        public CustomViewHolder(View itemView, ProductListener listener) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            prodName = itemView.findViewById(R.id.prod_name);
            this.listener = listener;
        }

        public void bind(int position) {
            Product product = mData.get(position);
            prodName.setText(product.getName());
            img.setOnClickListener(v -> {
                this.listener.onClick(product);
            });
            //用Glide加载网络图片
            NetworkGlideImageLoader.getInstance().displayImage((Activity) mContext, UrlUtils.getPicUrl(product.getPicName()), img, 0, 0);
        }

    }

    public interface ProductListener {
        void onClick(Product product);
    }
}
