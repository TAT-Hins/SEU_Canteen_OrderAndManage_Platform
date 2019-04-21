package com.seu.cose.seu_comp.layout.Base.TranslucentStatusBar;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.seu.cose.seu_comp.Override.Base.COMP_Application;
import com.seu.cose.seu_comp.R;

public class WindowTranslucent {

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 生成一个和状态栏大小相同的半透明矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @param alpha    透明值
     * @return 状态栏矩形条
     */
    public static View createStatusBarView(Activity activity, @ColorInt int color, int alpha) {
        // 绘制一个和状态栏一样高的矩形
        View statusBarView = new View(activity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        int id = View.generateViewId();
        statusBarView.setId(id);
        return statusBarView;
    }

    public static View setTranslucent(Activity activity, ViewGroup vg) {
        return setTranslucent(activity, vg, new View(activity));
    }

    public static View setTranslucent(Activity activity, ViewGroup vg, View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ((COMP_Application) activity.getApplication()).STATUS_BAR_HEIGHT
        );
        return setTranslucent(activity, vg, view, lp);
    }

    public static View setTranslucent(Activity activity, ViewGroup vg, View view, LinearLayout.LayoutParams lp) {
        vg.addView(view, 0, lp);
        return view;
    }

}
