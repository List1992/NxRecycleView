package com.superdroid.nxrecycleview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import cn.jpush.android.api.InstrumentedActivity;

/**
 * Created by lisongtao on 2016/8/16 15:00.
 * desc：闪屏页面
 */
public class SplashActivity extends InstrumentedActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // mHandler.sendEmptyMessageDelayed(0, 1000);

        new Handler() {
        }.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 1500);
    }

    public Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    };
}
