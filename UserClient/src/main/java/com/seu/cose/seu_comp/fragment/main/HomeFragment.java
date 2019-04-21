package com.seu.cose.seu_comp.fragment.main;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.layout.Base.TranslucentStatusBar.WindowTranslucent;
import com.seu.cose.seu_comp.viewmodel.main.HomeFragmentModel;

public class HomeFragment extends BaseFragment_Main {

    private HomeFragmentModel homeModel;

    private LinearLayout mTopLocationBar;
    private SearchView mSearchView;
    private TextView mMessage;
    private ImageButton mButton;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        mSearchView.setPadding(
                mSearchView.getPaddingLeft(),
                (mSearchView.getPaddingTop() + mTopLocationBar.getHeight()),
                mSearchView.getPaddingRight(),
                mSearchView.getPaddingBottom()
        );

        // 设置字体为汉仪旗黑
        mMessage.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/hy_qihei.ttf"));

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 主fragment数据模型
        homeModel = ViewModelProviders.of(this).get(HomeFragmentModel.class);
    }

    @Override
    protected void initView() {
        super.initView();
        // 设置头布局
        mTopLayout = (AppBarLayout) getActivity().findViewById(R.id.top_layout_home);

        mTopLocationBar = (LinearLayout) getActivity().findViewById(R.id.topbar_home);
        mButton = (ImageButton) getActivity().findViewById(R.id.image_button_home);
        mSearchView = (SearchView) getActivity().findViewById(R.id.search_home);

        mMessage = (TextView) getActivity().findViewById(R.id.message_fragment_home);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

}
