package com.seu.cose.seu_comp.activity;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.seu.cose.seu_comp.Override.Base.BaseAppCompatActivity;
import com.seu.cose.seu_comp.layout.main.BottomNavigationViewHelper;
import com.seu.cose.seu_comp.layout.main.MainViewPager;
import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.adapter.main.MainFragmentAdapter;
import com.seu.cose.seu_comp.fragment.main.DishesFragment;
import com.seu.cose.seu_comp.fragment.main.HomeFragment;
import com.seu.cose.seu_comp.fragment.main.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Client extends BaseAppCompatActivity {

    private MainViewPager vp;

    private boolean mIsExit;    //预退出状态标识

    private static final Fragment[] FRAGMENTS_CLASS = {
            HomeFragment.newInstance(),
            DishesFragment.newInstance(),
            MineFragment.newInstance()
    };

    private static final int TAG_FRAG_HOME = 0;
    private static final int TAG_FRAG_DISHES = 1;
    private static final int TAG_FRAG_MINE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = (MainViewPager) findViewById(R.id.viewpager_main);

        List<Fragment> frags_main = new ArrayList<Fragment>();
        //添加fragment列表
        for (Fragment f : FRAGMENTS_CLASS){
            frags_main.add(f);
        }

        // 设置fragment适配器
        MainFragmentAdapter fragAdapter = new MainFragmentAdapter(getSupportFragmentManager(), frags_main);
        vp.setAdapter(fragAdapter);
        vp.setCurrentItem(TAG_FRAG_HOME);   //设置默认定位的fragment
        vp.setOffscreenPageLimit(FRAGMENTS_CLASS.length - 1);   // 设置页面数量限制

        //底部导航栏初始化
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.home_bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        vp.addOnPageChangeListener(new MainViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {}

            @Override
            public void onPageSelected(int i) {}

            @Override
            public void onPageScrollStateChanged(int i) {}
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    vp.setCurrentItem(TAG_FRAG_HOME);
                    return true;
                case R.id.nav_dishes:
                    vp.setCurrentItem(TAG_FRAG_DISHES);
                    return true;
                case R.id.nav_mine:
                    vp.setCurrentItem(TAG_FRAG_MINE);
                    return true;
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

}
