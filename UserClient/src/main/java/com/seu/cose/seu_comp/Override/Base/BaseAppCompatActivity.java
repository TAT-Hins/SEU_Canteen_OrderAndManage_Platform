package com.seu.cose.seu_comp.Override.Base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;

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

    protected void setStatusBarTranslucent() {
        View decorView = getWindow().getDecorView();
        //让应用主题内容占用系统状态栏的空间,注意:下面两个参数必须一起使用 stable 牢固的
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        //设置状态栏颜色为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

}
