package com.seu.cose.seu_comp.viewmodel.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.seu.cose.seu_comp.Override.Base.NetCommunication;
import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.entity.Base.AccessResult;
import com.seu.cose.seu_comp.entity.Base.CanteenWindowInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MenuFragmentModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<Map<String, Map<String, ArrayList<CanteenWindowInfo>>>> windowMenu;

    /*
     * where = 橘园 / 梅园 / 桃园
     */
    public LiveData<Map<String, Map<String, ArrayList<CanteenWindowInfo>>>> getWindowMenu() {
        if (windowMenu == null){
            windowMenu = new MutableLiveData<>();//初始化
            obtainWindowMenu();     //获取窗口信息
        }
        return windowMenu;
    }

    private void obtainWindowMenu() {

        boolean isSuccess = false;

        // 获取推荐菜品信息回调
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        };

        Map<String, Map<String, ArrayList<CanteenWindowInfo>>> menu = NetCommunication.post_NewThread(null, R.string.server_host_url + "/getWindows", callback,
                new AccessResult<Map<String, Map<String, ArrayList<CanteenWindowInfo>>>>())
                .getResponseBody();    //多线程方法
        windowMenu.setValue(menu);
    }

}
