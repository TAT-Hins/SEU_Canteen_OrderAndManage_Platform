package com.seu.cose.seu_comp.fragment.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.viewmodel.main.DishesFragmentModel;

public class DishesFragment extends Fragment {

    private DishesFragmentModel dishesModel;

    public static DishesFragment newInstance() {
        return new DishesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dishes, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 主fragment数据模型
        dishesModel = ViewModelProviders.of(this).get(DishesFragmentModel.class);


    }

}
