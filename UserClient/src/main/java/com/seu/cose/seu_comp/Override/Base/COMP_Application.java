package com.seu.cose.seu_comp.Override.Base;

import android.app.Activity;
import android.app.Application;
import android.os.Environment;

import com.seu.cose.seu_comp.entity.Base.User;

import java.util.ArrayList;

// 单例模式
public class COMP_Application extends Application {

    // 通用参数
    public static final int CANTEEN_NUM  = 3;
    public static final int JUYUAN = 0;     //橘园
    public static final int MEIYUAN = 1;    //梅园
    public static final int TAOYUAN = 2;    //桃园

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
    }

    // 获取路径
    public String getAbsolutePath(){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        } else return null;
    }

}
