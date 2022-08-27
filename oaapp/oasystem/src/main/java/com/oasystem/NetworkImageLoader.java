package com.oasystem;

import android.app.Activity;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * 网络图片加载器接口，外部需要实现这个类去加载图片
 */
public interface NetworkImageLoader extends Serializable {
    void displayImage(Activity activity, String path, ImageView imageView, int width, int height);
}