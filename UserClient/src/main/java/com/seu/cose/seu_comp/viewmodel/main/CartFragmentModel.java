package com.seu.cose.seu_comp.viewmodel.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.seu.cose.seu_comp.Override.Base.COMP_Application;
import com.seu.cose.seu_comp.Override.Base.NetCommunication;
import com.seu.cose.seu_comp.entity.Base.AccessResult;
import com.seu.cose.seu_comp.entity.Base.Dish;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CartFragmentModel extends ViewModel {

    // TODO: Implement the ViewModel

    private ArrayList<MutableLiveData<List<Dish>>> cart;

    /*
     * where = 橘园 / 梅园 / 桃园
     */
    public LiveData<List<Dish>> getDishes(int where) {
        if (where != COMP_Application.JUYUAN
                && where != COMP_Application.MEIYUAN
                && where != COMP_Application.TAOYUAN) {
            // 不是餐厅之一
            return null;
        }
        if (cart == null){
            cart = new ArrayList<>(COMP_Application.CANTEEN_NUM);  //初始化
        }
        return cart.get(where);
    }

}
