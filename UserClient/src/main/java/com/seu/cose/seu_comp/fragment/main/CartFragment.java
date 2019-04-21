package com.seu.cose.seu_comp.fragment.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.viewmodel.main.CartFragmentModel;

public class CartFragment extends BaseFragment_Main {

    private CartFragmentModel cartModel;

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 主fragment数据模型
        cartModel = ViewModelProviders.of(this).get(CartFragmentModel.class);

    }

    @Override
    protected void initView() {
        // 设置头布局
        mTopLayout = (LinearLayout) getActivity().findViewById(R.id.top_layout_cart);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cart;
    }

}
