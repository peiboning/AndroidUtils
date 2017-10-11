package com.android.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by peiboning on 2017/8/23.
 */

public class ScrollerCircleView extends LinearLayout{

    private Scroller mSroller;
    private CircleView mView;
    private int startY;

    public ScrollerCircleView(Context context) {
        super(context);
        init();
    }



    public ScrollerCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollerCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSroller = new Scroller(getContext());
    }

    public void startScroll(){
        startY = getHeight()/10;
        startScroll(0);
    }
    public void startScroll(int temp){
        mView = (CircleView) getChildAt(1);
        int bottom = getHeight()-temp - 2*mView.getRadius();
        Log.e("peibn", "getHeight: " + getHeight());
        Log.e("peibn", "begin:" + temp + "  end:" + bottom);
        mSroller.startScroll(getScrollX(),-temp,0,-bottom, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(mSroller.computeScrollOffset()){
            int curX = mSroller.getCurrX();
            int curY = mSroller.getCurrY();
            scrollTo(curX, curY);
            postInvalidate();
        }else{
            if(null != mView){
                startY = startY*2;
                if(startY + 3*mView.getRadius()< getHeight()){
                    startScroll(startY);
                }
                Log.e("peibn", "scroll over......");
            }
        }
    }
}
