package com.seu.cose.seu_comp.fragment.main;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seu.cose.seu_comp.Override.Base.BaseFragment;
import com.seu.cose.seu_comp.activity.MainActivity;

public abstract class BaseFragment_Main extends BaseFragment {

    protected MainActivity mActivity;

    protected ViewGroup mTopLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.setStatusBarBackground(mTopLayout.getBackground());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mActivity = (MainActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public interface OnBaseMainFragmentListener {
        void setStatusBarBackground(Drawable drawable);
    }

    protected abstract int getLayoutId();
    protected void initView() {}

    public Drawable getTopLayoutBackground() {
        return mTopLayout.getBackground();
    }

}
