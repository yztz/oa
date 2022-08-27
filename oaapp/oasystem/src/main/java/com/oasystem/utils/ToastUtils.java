package com.oasystem.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public static void show(Context context, String content){
        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }

}
