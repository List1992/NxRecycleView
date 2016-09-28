package com.superdroid.nxrecycleview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.superdroid.nxrecycleview.activity.TestDialogActivity;
import com.superdroid.nxrecycleview.utils.ExampleUtil;
import com.superdroid.nxrecycleview.utils.MyEventBus;
import com.superdroid.nxrecycleview.utils.Toastutils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.JPushInterface;


public class MainActivity extends InstrumentedActivity {

    @BindView(R.id.initJpush)
    Button mInitJpush;
    @BindView(R.id.stopJpush)
    Button mStopJpush;
    @BindView(R.id.resumeJpush)
    Button mResumeJpush;
    @BindView(R.id.getID)
    Button mGetID;
    @BindView(R.id.ID)
    TextView mID;
    @BindView(R.id.toDialog)
    Button mToDialog;
    private long mExitTime;

    public static boolean isForeground = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //注册广播接收者
        registerMessageReceiver();

        EventBus.getDefault().register(this);

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

    @OnClick({R.id.initJpush, R.id.stopJpush, R.id.resumeJpush, R.id.getID})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.initJpush:
                Toastutils.showToast(this, "初始化");
                JPushInterface.init(getApplicationContext());
                break;
            case R.id.stopJpush:
                Toastutils.showToast(this, "停止推送");
                JPushInterface.stopPush(getApplicationContext());
                break;
            case R.id.resumeJpush:
                Toastutils.showToast(this, "恢复推送");
                JPushInterface.resumePush(getApplicationContext());
                break;
            case R.id.getID:
                String registrationID = JPushInterface.getRegistrationID(getApplicationContext());
                if (!TextUtils.isEmpty(registrationID)) {
                    //获取用户唯一标识
                    mID.setText(registrationID);
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        isForeground = true;

        super.onResume();

        Log.i("JPush", "onresume");
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
        Log.i("JPush", "onpause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("JPush", "onstop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMessageReceiver);
    }

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    //自定义广播的action
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    @OnClick(R.id.toDialog)
    public void onClick() {

        startActivity(new Intent(MainActivity.this, TestDialogActivity.class));
    }

    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }

                // mID.setText(showMsg.toString());
            }
        }
    }

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshView(MyEventBus mMyEventBus) {
        String msg = mMyEventBus.getMsg();
        mID.setText(msg);
    }
}
