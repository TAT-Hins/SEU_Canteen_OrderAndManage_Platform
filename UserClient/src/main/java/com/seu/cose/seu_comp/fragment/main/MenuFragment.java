package com.seu.cose.seu_comp.fragment.main;

import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
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
import android.widget.TextView;

import com.seu.cose.seu_comp.Override.Base.DisplayInfo;
import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.entity.Base.CanteenWindowInfo;
import com.seu.cose.seu_comp.layout.Base.TitleBar.TitleBar;
import com.seu.cose.seu_comp.layout.Menu.MenuWindowIconView;
import com.seu.cose.seu_comp.viewmodel.main.MenuFragmentModel;

import java.util.ArrayList;
import java.util.Map;

public class MenuFragment extends BaseFragment_Main {

    private MenuFragmentModel menuModel;

    private TitleBar mTitleBar;
    private ScrollView mScrollView;
    private LinearLayout mScrollLayout;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 主fragment数据模型
        menuModel = ViewModelProviders.of(this).get(MenuFragmentModel.class);
        // 设置菜单视图
        //addMenuView(menuModel.getWindowMenu().getValue());

    }

    @Override
    protected void initView() {
        // 设置头布局
        mTopLayout = (LinearLayout) getActivity().findViewById(R.id.top_layout_menu);

        mTitleBar = (TitleBar) getActivity().findViewById(R.id.titlebar_menu);
        mScrollView = (ScrollView) getActivity().findViewById(R.id.scroll_menu);
        mScrollLayout = (LinearLayout) getActivity().findViewById(R.id.scroll_layout_menu);


    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu;
    }

    private void addMenuView(Map<String, Map<String, ArrayList<CanteenWindowInfo>>> canteenWindows) {

        for (Map.Entry<String, Map<String, ArrayList<CanteenWindowInfo>>> canteenEntry
                : canteenWindows.entrySet()){
            // 设置一级目录（餐厅名）
            LinearLayout firstLayout = new LinearLayout(getContext());
            firstLayout.setOrientation(LinearLayout.VERTICAL);

            TextView canteenTitle = new TextView(getContext());
            canteenTitle.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            ));
            canteenTitle.setText(canteenEntry.getKey());    // 设置餐厅名标题

            for (Map.Entry<String, ArrayList<CanteenWindowInfo>> floorEntry
                    : canteenEntry.getValue().entrySet()) {
                // 设置二级目录（楼层）
                LinearLayout secondLayout = new LinearLayout(getContext());
                secondLayout.setOrientation(LinearLayout.VERTICAL);

                TextView floorTitle = new TextView(getContext());
                floorTitle.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                ));
                floorTitle.setText(floorEntry.getKey());

                GridLayout floorGrid = new GridLayout(getContext());
                floorGrid.setRowCount(3);


                for (int i = 0; i < floorEntry.getValue().size(); ++i) {
                    // 设置窗口图标
                    floorGrid.addView(
                            //addWindowIcon(floorEntry.getValue().get(i))
                            new MenuWindowIconView(getContext(), null,
                                    floorEntry.getValue().get(i).getWindowIconUrl(),
                                    floorEntry.getValue().get(i).getWindowName())
                    );
                }

                firstLayout.addView(secondLayout);
            }

            mScrollLayout.addView(firstLayout);
        }
    }

    private LinearLayout addWindowIcon(CanteenWindowInfo info) {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        // 设置宽高参数
        ViewGroup.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        int marginDP = DisplayInfo.dp2px(getContext(), 10);
        ((LinearLayout.LayoutParams) lp).setMargins(marginDP, marginDP, marginDP, marginDP);
        // 应用
        layout.setLayoutParams(lp);

        ImageView icon = new ImageView(getContext());
        icon.setImageURI(Uri.parse(info.getWindowIconUrl()));

        TextView windowName = new TextView(getContext());
        windowName.setText(info.getWindowName());

        layout.addView(icon);
        layout.addView(windowName);
        return layout;
    }

}
