/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.superdroid.nxrecycleview;

import android.app.Application;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Yan Zhenjie on 2016/7/27.
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null) {
            instance = this;
        }

        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        Log.i("tao", "JPUSH IS ONCREATE");
    }

    public static App getInstance() {
        return instance;
    }
}
