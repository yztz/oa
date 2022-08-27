package com.oasystem;

import static com.oasystem.utils.RequestUtils.buildPostRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.oasystem.service.SharedpreferenceServcie;
import com.oasystem.service.WebService;
import com.oasystem.utils.RequestUtils;
import com.oasystem.utils.ToastUtils;
import com.oasystem.utils.UrlUtils;
import com.oasystem.utils.UserUtils;
import com.oasystem.view.ContactActivity;
import com.oasystem.view.UserCenterActivity;

import okhttp3.Request;

public class MainActivity extends Activity {
    private static final String TAG = "login";

    private EditText userName;
    private EditText password;
    private CheckBox remember;
    private TextView contact;

    private SharedpreferenceServcie sharedpreferenceServcie;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JSONObject res = (JSONObject) msg.obj;
            boolean success = res.getBoolean("success");

            if (success) {
                JSONObject data = res.getJSONObject("data");
                Log.d(TAG, "handleMessage: " + data);
                UserUtils.setToken(data.getString("token"));
//                User user = (User) bean.getObj();
//                if (user.getUserType().equals("0")) {
                Intent itent = new Intent(MainActivity.this, UserCenterActivity.class);
//                    itent.putExtra("id", user.getId());
//                    itent.putExtra("userId", user.getUserId());
//                    itent.putExtra("account", user.getAccount());
//                    itent.putExtra("userName", user.getUserName());
//                    itent.putExtra("password", user.getPassword());
//                    itent.putExtra("phone", user.getPhone());
//                    itent.putExtra("userType", user.getUserType());
//                    itent.putExtra("appendix", user.getAppendix());
                startActivity(itent);
                finish();
//                } else {
//                    ToastUtils.show(MainActivity.this, "您无权限登录!");
//                }

            } else {
                ToastUtils.show(MainActivity.this, res.getString("message"));
            }
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_main);


        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        remember = findViewById(R.id.remember);
        contact = findViewById(R.id.contact);

        contact.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
        });

//        webService = new WebService();
        sharedpreferenceServcie = new SharedpreferenceServcie(MainActivity.this);

        String res = sharedpreferenceServcie.read("remember");
        if (res.equals("yes")) {
            userName.setText(sharedpreferenceServcie.read("userName"));
            password.setText(sharedpreferenceServcie.read("password"));
            remember.setChecked(true);
        } else {

            remember.setChecked(false);
        }
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {//记住密码

                    sharedpreferenceServcie.save("remember", "yes");

                } else {//不记住密码
                    sharedpreferenceServcie.save("remember", "no");
                }
            }
        });
    }

    public void login(View v) {

        String username = userName.getText().toString().trim();
        String pwd = password.getText().toString().trim();

        if ("".equals(username) || "".equals(pwd)) {
            Toast.makeText(MainActivity.this, "用户名或密码不能为空!", Toast.LENGTH_SHORT).show();
        } else {//普通用户
            sharedpreferenceServcie.save("userName", username);
            sharedpreferenceServcie.save("password", pwd);

            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("password", pwd);

            Request request = buildPostRequest(UrlUtils.LOGIN, json);
            WebService.asyncGet(request, data -> {
                Message msg = Message.obtain();
                msg.obj = data;
                handler.sendMessage(msg);
            });


        }

    }
}