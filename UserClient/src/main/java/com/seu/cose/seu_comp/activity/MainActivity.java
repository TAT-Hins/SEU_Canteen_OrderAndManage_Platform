package com.seu.cose.seu_comp.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.seu.cose.seu_comp.Override.Base.BaseAppCompatActivity;
import com.seu.cose.seu_comp.Override.Base.COMP_Application;
import com.seu.cose.seu_comp.fragment.main.BaseFragment_Main;
import com.seu.cose.seu_comp.fragment.main.CartFragment;
import com.seu.cose.seu_comp.layout.main.BottomNavigationViewHelper;
import com.seu.cose.seu_comp.layout.main.MainViewPager;
import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.adapter.main.MainFragmentAdapter;
import com.seu.cose.seu_comp.fragment.main.MenuFragment;
import com.seu.cose.seu_comp.fragment.main.HomeFragment;
import com.seu.cose.seu_comp.fragment.main.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseAppCompatActivity
        implements BaseFragment_Main.OnBaseMainFragmentListener {

    private LinearLayout mContainer;
    private MainViewPager vp;
    private BottomNavigationView navigation;

    public View mStatusBarView;

    private MainFragmentAdapter fragAdapter;

    private boolean mIsExit;    //预退出状态标识

    private static final BaseFragment_Main[] FRAGMENTS_CLASS = {
            HomeFragment.newInstance(),
            MenuFragment.newInstance(),
            CartFragment.newInstance(),
            MineFragment.newInstance()
    };

    // 参数
    private static final int TAG_FRAG_HOME = 0;
    private static final int TAG_FRAG_MENU = 1;
    private static final int TAG_FRAG_CART = 2;
    private static final int TAG_FRAG_MINE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // 设置透明
        setStatusBarTranslucent();

        List<Fragment> frags_main = new ArrayList<Fragment>();
        //添加fragment列表
        for (Fragment f : FRAGMENTS_CLASS){
            frags_main.add(f);
        }



        // 设置fragment适配器
        fragAdapter = new MainFragmentAdapter(getSupportFragmentManager(), frags_main);
        vp.setAdapter(fragAdapter);
        vp.setCurrentItem(TAG_FRAG_HOME);   //设置默认定位的fragment
        vp.setOffscreenPageLimit(FRAGMENTS_CLASS.length - 1);   // 设置页面数量限制

        //底部导航栏初始化
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        vp.addOnPageChangeListener(new MainViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {}

            @Override
            public void onPageSelected(int i) { }

            @Override
            public void onPageScrollStateChanged(int i) {}
        });

    }

    private void initView() {
        mContainer = (LinearLayout) findViewById(R.id.container_main);
        mStatusBarView = (View) findViewById(R.id.fill_status_bar_main);
        vp = (MainViewPager) findViewById(R.id.viewpager_main);
        navigation = (BottomNavigationView) findViewById(R.id.home_bottom_navigation);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    return setFragmentPageView(TAG_FRAG_HOME);
                case R.id.nav_menu:
                    return setFragmentPageView(TAG_FRAG_MENU);
                case R.id.nav_cart:
                    return setFragmentPageView(TAG_FRAG_CART);
                case R.id.nav_mine:
                    return setFragmentPageView(TAG_FRAG_MINE);
            }
            return false;
        }

    };

    /*
     * 返回上一个窗口
     * 若已是第一个窗口则退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                for(Activity act:app.activities) {
                    act.finish();//显式结束
                }
                Toast.makeText(getApplicationContext(), getString(R.string.toast_hint_exit_success), Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, getString(R.string.toast_hint_exit_warning), Toast.LENGTH_SHORT).show();
                mIsExit = true;
                // 两秒后无操作则重置预退出状态
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private Boolean setFragmentPageView(int pos) {
        vp.setCurrentItem(pos);
        mStatusBarView.setBackground(
                FRAGMENTS_CLASS[pos].getTopLayoutBackground());
        return true;
    }

    /**
     *
     * @param drawable
     */
    @Override
    public void setStatusBarBackground(Drawable drawable) {
        if (mStatusBarView != null) {
            mStatusBarView.setBackground(drawable);
        } else {
            System.out.println("填充栏未初始化！");
        }
    }

    /**
     * setStatusBar()
     * 必须先初始化mTopLayout，绑定头布局
     */
    protected void setStatusBar() {
        if (mStatusBarView != null){
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ((COMP_Application) getApplication()).STATUS_BAR_HEIGHT
            );
            mStatusBarView.setLayoutParams(lp);
            //mStatusBarView.setBackground(mTopLayout.getBackground());
            mStatusBarView.requestLayout();
        } else {
            System.out.println("界面未初始化");
        }
    }

}
