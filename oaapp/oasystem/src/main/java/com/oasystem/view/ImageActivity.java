package com.oasystem.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.oasystem.NetworkGlideImageLoader;
import com.oasystem.R;
import com.oasystem.bean.Product;
import com.oasystem.service.WebService;
import com.oasystem.utils.UrlUtils;

import java.io.Serializable;

public class ImageActivity extends Activity {

    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_image);

        photoView = findViewById(R.id.photo_view);
        photoView.setOnPhotoTapListener((view, x, y) -> {
            finish();
        });

        displayPicture();
    }


    public void displayPicture(){
        Product product = (Product) this.getIntent().getSerializableExtra("product");
        NetworkGlideImageLoader.getInstance().displayImage(this, UrlUtils.getPicUrl(product.getPicName()), photoView);
    }
}