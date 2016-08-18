package com.superdroid.nxrecycleview.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by 李松涛 on 2016/6/10 14:05.
 * 描述：自定义吐司，防止重复
 */
public class Toastutils extends Toast {

    private static Toast mToast;

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public Toastutils(Context context) {
        super(context);
    }

    public static void showToast(final Activity act, final String text) {

        String threadname = Thread.currentThread().getName();
        if ("main".equals(threadname)) {
            //如果是在主线程中，直接弹吐司
            //如果还没有toast实例，就创建一个
            if (mToast == null) {
                mToast = Toast.makeText(act, text, Toast.LENGTH_SHORT);
            } else {
                //如果有toast实例，就直接使用
                mToast.setText(text);
                mToast.setDuration(Toast.LENGTH_SHORT);
            }
            mToast.show();
        } else {
            //如果不是在主线程中
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(act, text, Toast.LENGTH_SHORT);
                    } else {
                        mToast.setText(text);
                        mToast.setDuration(Toast.LENGTH_SHORT);
                    }
                    mToast.show();
                }
            });
        }

    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }


}
