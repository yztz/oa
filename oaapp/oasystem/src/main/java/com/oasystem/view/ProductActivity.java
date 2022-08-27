package com.oasystem.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.oasystem.NetworkImageAdapter;
import com.oasystem.R;
import com.oasystem.bean.Product;
import com.oasystem.bean.ProductWithPage;
import com.oasystem.service.WebService;
import com.oasystem.utils.ParseUtils;
import com.oasystem.utils.RequestUtils;
import com.oasystem.utils.ScreenUtils;
import com.oasystem.utils.UrlUtils;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.Request;

public class ProductActivity extends Activity {
    private static final String TAG = "NEWS";

    private String categoryName = "";//存储类型名称
    private Long categoryID;//存储类型id

    private RefreshLayout refreshLayout;
    private ClassicsFooter footer;



    private TextView title;//题目
    private RecyclerView recyclerView;
    private List<Product> products;
    private NetworkImageAdapter myAdapter;

    private int pageSize;
    private int nextPageIdx = 1;

    private void calPageSize() {
        int dimen = (int) this.getResources().getDimension(R.dimen.prod_img_size);
        this.pageSize = ScreenUtils.getScreenHeight(this) / dimen + 2;
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JSONObject res = (JSONObject) msg.obj;
            boolean success = res.getBoolean("success");
            if (success) {
                ProductWithPage result = res.getObject("data", ProductWithPage.class);
                List<Product> productList = result.getData();

                Log.d(TAG, "=====new coming products====");
                for (Product p : productList)
                    Log.d(TAG, "products: " + p);
                Log.d(TAG, "=============end============");


                if(refreshLayout.isRefreshing()) {
                    products.clear();
                    products.addAll(productList);
                    myAdapter.notifyDataSetChanged();
                    refreshLayout.finishRefresh(500);
                } else {
                    int oldSize = products.size();
                    int addSize = productList.size();
                    products.addAll(productList);
                    myAdapter.notifyItemRangeInserted(oldSize,addSize);
                    refreshLayout.finishLoadMore(500, true, addSize < pageSize);
                }

            } else {
                Log.d(TAG, "handleMessage: fail");
//                refreshLayout.finishLoadMore(false);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_news_list);

        calPageSize();

        categoryName = getIntent().getStringExtra("cname");
        categoryID = getIntent().getLongExtra("cid", -1);

        title = findViewById(R.id.title);
        title.setText(categoryName);

        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            loadMore();
        });
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            reload();
        });

        footer = findViewById(R.id.footer);
        footer.setFinishDuration(0);

        products = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



//        photoView.setOnPhotoTapListener((view, x, y) -> hidePhoto());

        myAdapter = new NetworkImageAdapter(this, products, this::showPhoto);
        recyclerView.setAdapter(myAdapter);
        loadMore();
    }



    private void showPhoto(Product product) {
        Intent intent = new Intent(ProductActivity.this, ImageActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }

//    private void hidePhoto() {
//        photoView.setVisibility(View.GONE);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(photoView, "Alpha", 0f).setDuration(200);
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                photoView.setVisibility(View.INVISIBLE);
//            }
//        });
//        animator.start();
//    }

    private void reload() {
        nextPageIdx = 1;
        loadMore();
    }

    private void loadMore() {
        JSONObject object = new JSONObject();
        object.put("cid", categoryID);
        object.put("pageIndex", nextPageIdx++);
        object.put("pageSize", pageSize);
        Request request = RequestUtils.buildGetRequest(UrlUtils.PRODUCT_QUERY, object);
        WebService.asyncGet(request, res -> {
            Message msg = Message.obtain();
            msg.obj = res;
            handler.sendMessage(msg);
        });
    }

//    @Override
//    public void onBackPressed() {
//        if(isPhotoShow()) {
//            hidePhoto();
//        } else {
//            super.onBackPressed();
//        }
//    }
}