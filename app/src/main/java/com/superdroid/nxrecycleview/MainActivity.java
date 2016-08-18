package com.superdroid.nxrecycleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

import com.superdroid.nxrecycleview.utils.Toastutils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends Activity {


    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断按下的按键是不是返回键
        if (keyCode == event.KEYCODE_BACK) {

            if (System.currentTimeMillis() - mExitTime > 2000) {
                // 如果当前点击返回键的时间减去上一次点击返回键的时间大于2秒
                Toastutils.showToast(this, "再点一次退出");
                //重新给上一次点击的时间赋值，方便下次使用
                mExitTime = System.currentTimeMillis();
            } else {
                //如果小于两秒，就直接退出
                finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
