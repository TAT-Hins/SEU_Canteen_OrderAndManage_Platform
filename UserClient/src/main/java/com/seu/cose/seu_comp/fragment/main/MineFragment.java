package com.seu.cose.seu_comp.fragment.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.activity.MainActivity_Client;
import com.seu.cose.seu_comp.layout.Base.TitleBar.OnTitleBarListener;
import com.seu.cose.seu_comp.layout.Base.TitleBar.TitleBar;
import com.seu.cose.seu_comp.viewmodel.main.MineFragmentModel;

public class MineFragment extends Fragment {

    private MineFragmentModel mineModel;

    private ScrollView mScrollView;
    private TitleBar mTitleBar;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

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

    private void initView() {
        mScrollView = (ScrollView) getActivity().findViewById(R.id.scrollview_fragment_mine);
        mTitleBar = (TitleBar) getActivity().findViewById(R.id.titlebar_fragment_mine);
    }
}
