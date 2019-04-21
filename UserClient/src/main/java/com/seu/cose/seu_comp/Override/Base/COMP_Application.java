package com.seu.cose.seu_comp.Override.Base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.seu.cose.seu_comp.entity.Base.User;
import com.seu.cose.seu_comp.layout.Base.TranslucentStatusBar.WindowTranslucent;

import java.lang.reflect.Field;
import java.util.ArrayList;

// 单例模式
public class COMP_Application extends Application {

    // 通用参数
    public static final int CANTEEN_NUM  = 3;
    public static final int JUYUAN = 0;     //橘园
    public static final int MEIYUAN = 1;    //梅园
    public static final int TAOYUAN = 2;    //桃园
    public static int STATUS_BAR_HEIGHT = 0;

    private User currentUser;
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    public User getCurrentUser() {
        return this.currentUser;
    }

    public ArrayList<Activity> activities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        STATUS_BAR_HEIGHT = WindowTranslucent.getStatusBarHeight(getApplicationContext());
    }

    // 获取路径
    public String getAbsolutePath(){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        } else return null;
    }



}
