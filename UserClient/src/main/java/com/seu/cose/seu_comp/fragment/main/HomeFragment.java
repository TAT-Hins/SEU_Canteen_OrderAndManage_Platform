package com.seu.cose.seu_comp.fragment.main;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.viewmodel.main.HomeFragmentModel;

public class HomeFragment extends Fragment {

    private HomeFragmentModel homeModel;
    private TextView mMessage;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMessage = (TextView) getActivity().findViewById(R.id.message_fragment_home);

        // 主fragment数据模型
        homeModel = ViewModelProviders.of(this).get(HomeFragmentModel.class);

        // 设置字体为汉仪旗黑
        mMessage.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/hy_qihei.ttf"));

    }

}
