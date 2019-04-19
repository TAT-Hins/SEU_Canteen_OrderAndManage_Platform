package com.seu.cose.seu_comp.Override.Base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.Toast;

/*
 * 所有Activity的基类
 * 添加统一使用的方法（如添加到Activity池等）
 */
public class BaseAppCompatActivity extends AppCompatActivity {

    protected COMP_Application app;
    protected DisplayMetrics displayInfo;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 获取应用程序全局的实例引用
        app = (COMP_Application) getApplication();
        // 把当前Activity放入集合中
        app.activities.add(this);
        // 获取屏幕显示信息
        displayInfo = app.getResources().getDisplayMetrics();

        // 仅竖屏显示
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void onDestroy(){
        COMP_Application app = (COMP_Application) getApplication(); //获取应用程序全局的实例引用
        app.activities.remove(this);    //把当前Activity从集合中移除
        super.onDestroy();
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event){
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}
