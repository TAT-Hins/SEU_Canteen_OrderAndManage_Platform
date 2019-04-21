package com.seu.cose.seu_comp.fragment.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.layout.Base.TitleBar.OnTitleBarListener;
import com.seu.cose.seu_comp.layout.Base.TitleBar.TitleBar;
import com.seu.cose.seu_comp.viewmodel.main.MineFragmentModel;

public class MineFragment extends BaseFragment_Main {

    private MineFragmentModel mineModel;

    private ScrollView mScrollView;
    private TitleBar mTitleBar;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 主fragment数据模型
        mineModel = ViewModelProviders.of(this).get(MineFragmentModel.class);

        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) { }

            @Override
            public void onTitleClick(View v) { }

            @Override
            public void onRightClick(View v) {

            }
        });

    }

    protected void initView() {
        // 设置头布局
        mTopLayout = (LinearLayout) getActivity().findViewById(R.id.top_layout_mine);

        mScrollView = (ScrollView) getActivity().findViewById(R.id.scroll_mine);
        mTitleBar = (TitleBar) getActivity().findViewById(R.id.titlebar_mine);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

}
