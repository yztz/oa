package com.oasystem.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.oasystem.MyBannerAdapter;
import com.oasystem.R;
import com.oasystem.bean.Category;
import com.oasystem.bean.Product;
import com.oasystem.bean.ProductWithPage;
import com.oasystem.service.WebService;
import com.oasystem.utils.RequestUtils;
import com.oasystem.utils.UrlUtils;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.indicator.Indicator;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class UserCenterActivity extends Activity {
    private static String TAG = "usercenter";

    private int bannerSize = 5;

    private GridView gridView;
    private List<Category> classifyList;
    private Banner<Product, MyBannerAdapter> banner;


    private MyAdapter myAdapter;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JSONObject data = (JSONObject)msg.obj;
            classifyList = data.getJSONArray("data").toJavaList(Category.class);
            Log.d(TAG, classifyList.toString());
            myAdapter = new MyAdapter(classifyList);
            gridView.setAdapter(myAdapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_user_center);

        banner = findViewById(R.id.mybanner);
        banner.setIndicator(new CircleIndicator(this));

        gridView = this.findViewById(R.id.gridview);
        classifyList = new ArrayList<>();
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Category category = classifyList.get(position);
            Intent intent = new Intent(UserCenterActivity.this, ProductActivity.class);
            intent.putExtra("cname", category.getName());
            intent.putExtra("cid", category.getId());
            startActivity(intent);

        });
        loadNewest();
        loadClassify();
    }

    private void loadNewest() {
        JSONObject object = new JSONObject();
        object.put("pageIndex", 1);
        object.put("pageSize", bannerSize);
        Request request = RequestUtils.buildGetRequest(UrlUtils.PRODUCT_QUERY, object);

        WebService.asyncGet(request, res->{
            boolean success = res.getBoolean("success");
            if(success) {
                ProductWithPage result = res.getObject("data", ProductWithPage.class);
                List<Product> productList = result.getData();
                MyBannerAdapter adapter = new MyBannerAdapter(productList);
                UserCenterActivity.this.runOnUiThread(()->{
                    banner.setAdapter(adapter);
                });
            }

        });
    }

    private void loadClassify() {
        Request request = RequestUtils.buildGetRequest(UrlUtils.CATEGORY_QUERY_ALL, null);

        WebService.asyncGet(request, data -> {
            Message msg = Message.obtain();
            msg.obj = data;
            handler.sendMessage(msg);
        });
    }

    private class MyAdapter extends BaseAdapter {

        private List<Category> classifyList;

        public MyAdapter(List<Category> classifyList) {
            this.classifyList = classifyList;
        }

        @Override
        public int getCount() {
            return this.classifyList.size();
        }

        @Override
        public Object getItem(int position) {
            return this.classifyList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;

            if (view == null) {
                view = View.inflate(UserCenterActivity.this, R.layout.classify_item, null);
            } else {
                view = convertView;
            }
            Category bean = this.classifyList.get(position);

            TextView title = view.findViewById(R.id.title);

            title.setText(bean.getName());
            return view;
        }
    }
}
