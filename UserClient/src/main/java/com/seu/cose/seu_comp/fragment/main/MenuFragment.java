package com.seu.cose.seu_comp.fragment.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.entity.Base.CanteenWindowInfo;
import com.seu.cose.seu_comp.layout.Base.TitleBar.TitleBar;
import com.seu.cose.seu_comp.viewmodel.main.MenuFragmentModel;

import java.util.ArrayList;
import java.util.Map;

public class MenuFragment extends BaseFragment_Main {

    private MenuFragmentModel menuModel;

    private ScrollView mScrollView;
    private TitleBar mTitleBar;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 主fragment数据模型
        menuModel = ViewModelProviders.of(this).get(MenuFragmentModel.class);
        // 设置菜单视图
        //addMenuView();

    }

    @Override
    protected void initView() {
        // 设置头布局
        mTopLayout = (LinearLayout) getActivity().findViewById(R.id.top_layout_menu);

        mScrollView = (ScrollView) getActivity().findViewById(R.id.scroll_menu);
        mTitleBar = (TitleBar) getActivity().findViewById(R.id.titlebar_menu);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu;
    }

    private void addMenuView() {
        Map<String, Map<String, ArrayList<CanteenWindowInfo>>> canteenWindows = menuModel.getWindowMenu().getValue();
        for (Map.Entry<String, Map<String, ArrayList<CanteenWindowInfo>>> canteenEntry
                : canteenWindows.entrySet()){
            // 设置一级目录（餐厅名）
            GridLayout newLayout = new GridLayout(getContext());

            for (Map.Entry<String, ArrayList<CanteenWindowInfo>> floorEntry
                    : canteenEntry.getValue().entrySet()) {
                // 设置二级目录（楼层）

                for (CanteenWindowInfo info: floorEntry.getValue()){
                    // 设置图标

                }
            }
        }
    }

}
