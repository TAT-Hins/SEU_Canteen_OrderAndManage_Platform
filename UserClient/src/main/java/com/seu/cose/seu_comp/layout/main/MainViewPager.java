package com.seu.cose.seu_comp.layout.main;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MainViewPager extends ViewPager {

    private boolean scrollEnabled = false;//禁止滑动

    public MainViewPager(Context context) {
        super(context);
    }

    public MainViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll){
        this.scrollEnabled = isCanScroll;
    }

    @Override
    public void removeView(View view){
        //super.removeView(view);
    }

    @Override
    public void scrollTo(int x, int y){
        super.scrollTo(x, y);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (scrollEnabled) {//触摸时禁止事件
            return super.onTouchEvent(arg0);
        } else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (scrollEnabled) {//...
            return super.onInterceptTouchEvent(arg0);
        } else {
            return false;
        }
    }
}
