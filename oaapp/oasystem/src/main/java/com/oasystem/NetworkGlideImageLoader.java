package com.oasystem;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.oasystem.utils.UserUtils;

public class NetworkGlideImageLoader implements NetworkImageLoader {
    private static final String TAG = "NetworkGlideImageLoader";
    private static NetworkGlideImageLoader mInstance;

    private NetworkGlideImageLoader() {

    }

    public static NetworkGlideImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (NetworkGlideImageLoader.class) {
                if (mInstance == null)
                    mInstance = new NetworkGlideImageLoader();
            }
        }
        return mInstance;
    }


    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Log.d(TAG, "path " + path);
        Log.d(TAG, "displayImage() ");
        GlideUrl url = new GlideUrl(path, new LazyHeaders.Builder()
                .addHeader("X-Token", UserUtils.getToken())
                .build());

        Glide.with(activity)                       //配置上下文
//                .load(Uri.fromFile(new File("/storage/emulated/0/DCIM/Video/VID_20200313_084219.mp4")))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示) 本地路径
                .load(url)                               //网络图片路径
//                    .listener(new RequestListener<String, GlideDrawable>() {
//                        @Override
//                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                            Logs.i("网络图片加载异常");
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                            return false;
//                        }
//                    })
//                .error(R.mipmap.default_image)           //设置错误图片
//                .placeholder(R.mipmap.default_image)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    public void displayImage(Activity activity, String path, ImageView imageView) {
        Log.d(TAG, "path " + path);
        Log.d(TAG, "displayImage() ");
        GlideUrl url = new GlideUrl(path, new LazyHeaders.Builder()
                .addHeader("X-Token", UserUtils.getToken())
                .build());

        Glide.with(activity)                       //配置上下文
                .load(url)                               //网络图片路径
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    public void displayImage(View view, String path, ImageView imageView) {
        Log.d(TAG, "path " + path);
        Log.d(TAG, "displayImage() ");
        GlideUrl url = new GlideUrl(path, new LazyHeaders.Builder()
                .addHeader("X-Token", UserUtils.getToken())
                .build());

        Glide.with(view)                       //配置上下文
                .load(url)                               //网络图片路径
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    public void displayImage(View view, String path, ImageView imageView, RequestOptions options) {
        Log.d(TAG, "path " + path);
        Log.d(TAG, "displayImage() ");
        GlideUrl url = new GlideUrl(path, new LazyHeaders.Builder()
                .addHeader("X-Token", UserUtils.getToken())
                .build());

        Glide.with(view)                       //配置上下文
                .load(url)                               //网络图片路径
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .apply(options)
                .into(imageView);
    }


}
