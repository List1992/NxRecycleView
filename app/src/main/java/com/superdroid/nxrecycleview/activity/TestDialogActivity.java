package com.superdroid.nxrecycleview.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.superdroid.nxrecycleview.R;
import com.superdroid.nxrecycleview.utils.Toastutils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lisongtao on 2016/8/26 15:59.
 * desc：
 */
public class TestDialogActivity extends AppCompatActivity {

    @BindView(R.id.notifyDialog)
    Button mNotifyDialog;
    @BindView(R.id.listDialog)
    Button mListDialog;
    @BindView(R.id.singleDialog)
    Button mSingleDialog;
    @BindView(R.id.multiDialog)
    Button mMultiDialog;
    @BindView(R.id.progressDialog)
    Button mProgressDialog;

    /**
     * Android中常用的对话框有通知对话框、列表对话框、单选对话框、多选对话框以及进度对话框。其中，通知对话框、列表对话框、单选以及多选对话框由AlertDialog.Builder创建，进度对话框由ProgressDialog创建。
     * 常用方法：
     * setIcon				设置对话框标题栏左侧的那个图标
     * setTitle				设置对话框标题栏的提示信息
     * setMessage			设置对话框主体部分的提示信息
     * setPositiveButton		设置确定按钮
     * setNegativeButton	设置取消按钮
     * setCancelable			设置对话框在点击返回键时是否会关闭
     * show					显示对话框
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testdialog_activity_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.notifyDialog, R.id.listDialog, R.id.singleDialog, R.id.multiDialog, R.id.progressDialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.notifyDialog://通知对话框
                setNotifyDialog();
                break;
            case R.id.listDialog://列表对话框
                setListDialog();
                break;
            case R.id.singleDialog://单选对话框
                setSingleDialog();
                break;
            case R.id.multiDialog://多选对话框
                break;
            case R.id.progressDialog://进度对话框
                break;
        }
    }

    /**
     * 设置单选对话框
     */
    private void setSingleDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.nx);
        builder.setTitle("请选择您要学习的编程语言");

        final String[] languages = new String[]{"Java", "C/C++", "iOS", ".Net", "PHP"};
        /*
         * 第一个参数 显示的可选项
		 * 第二参数 默认选中的索引
		 * 第三个参数 点击事件监听器
		 */
        builder.setSingleChoiceItems(languages, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String language = languages[i];
                Toastutils.showToast(TestDialogActivity.this, language);
            }
        });

        //设置确定按钮的点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toastutils.showToast(TestDialogActivity.this, "确定");
            }
        });
        //设置取消按钮的点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toastutils.showToast(TestDialogActivity.this, "取消");
            }
        });
        builder.show();
    }

    /**
     * 设置列表对话框
     */
    private void setListDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.nx);
        builder.setTitle("请选择要去的城市");
        //设置对话框点击返回键不关闭
        builder.setCancelable(false);

        final String[] cities = new String[]{"北京", "上海", "广州", "深圳", "杭州"};
        builder.setItems(cities, new DialogInterface.OnClickListener() {
            /*
             * 第一个参数代表对话框对象
             * 第二个参数是点击对象的索引
             */
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String city = cities[which];
                Toastutils.showToast(TestDialogActivity.this, "您选择的是：" + city);
            }
        });
        builder.show();
    }

    /**
     * 设置通知对话框
     */
    private void setNotifyDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.mipmap.nx);//设置图标
        builder.setTitle("小极");//设置标题
        builder.setMessage("这是通知对话框吗？");//设置内容

        //设置对话框点击返回键不关闭
        builder.setCancelable(false);

        //设置确定按钮的点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toastutils.showToast(TestDialogActivity.this, "确定");
            }
        });

        //设置取消按钮的点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toastutils.showToast(TestDialogActivity.this, "取消");
            }
        });
        //将对话框显示出来
        builder.show();
    }


}
