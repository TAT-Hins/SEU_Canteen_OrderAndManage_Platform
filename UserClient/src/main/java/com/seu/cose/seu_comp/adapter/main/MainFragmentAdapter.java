package com.seu.cose.seu_comp.adapter.main;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class MainFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private FragmentManager fragmentManager;

    public MainFragmentAdapter(FragmentManager fm, List<Fragment> fragments){
        super(fm);
        this.mFragments = fragments;
        this.fragmentManager = fm;
    }

    @Override
    public Fragment getItem(int arg0){
        return mFragments.get(arg0);
    }

    @Override
    public int getCount(){
        return mFragments.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int pos, @NonNull Object object){
        // 不回收fragment而用隐藏来取代，需要时重新调出
        fragmentManager.beginTransaction()
                .hide(mFragments.get(pos))
                .commit();
    }
}
