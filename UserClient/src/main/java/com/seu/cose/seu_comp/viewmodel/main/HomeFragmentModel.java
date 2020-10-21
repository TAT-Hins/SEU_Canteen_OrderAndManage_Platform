package com.seu.cose.seu_comp.viewmodel.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.seu.cose.seu_comp.Override.Base.NetCommunication;
import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.entity.Base.AccessResult;
import com.seu.cose.seu_comp.entity.Base.Dish;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeFragmentModel extends ViewModel {

    private MutableLiveData<List<Dish>> recommendedDishes;

    public LiveData<List<Dish>> getRecommendedDishes() {
        if (recommendedDishes == null){
            recommendedDishes = new MutableLiveData<List<Dish>>();  //初始化
            obtainRecommendation();     //获取推荐菜品信息
        }
        return recommendedDishes;
    }

    private void obtainRecommendation() {

        boolean isSuccess = false;

//        new Thread(){
//            public void run(){
//                recommendedDishes.setValue(
//                        NetCommunication.post(null, R.string.server_host_url + "/getRecommendation")
//                );
//            }
//        }.run();

        // 获取推荐菜品信息回调
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        };

        recommendedDishes.setValue(
                NetCommunication.post_NewThread(null, R.string.server_host_url + "/getRecommendation", callback,
                        new AccessResult<List<Dish>>())
                        .getResponseBody()    //多线程方法
        );
    }

}