package com.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peiboning on 2017/8/22.
 */

public class FlowLayout extends ViewGroup {
    private static final String TAG = FlowLayout.class.getSimpleName();
    private static final int MAX_VIEWS = 20;
    private static final int MAX_LINES = 5;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();//Math.min(, MAX_VIEWS);
        Log.e(TAG, "measure childCount :" + childCount);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int lineWidth = 0;
        int lineHeight = 0;
        int width = 0;
        int height = 0;

        for(int i = 0;i<childCount;i++){
            View child = getChildAt(i);
            if(child.getVisibility() != View.GONE){
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

                if(lineWidth + childWidth > sizeWidth){
                    width = Math.max(lineWidth, childWidth);
                    height = height + lineHeight;
                    lineHeight = childHeight;
                }else{
                    lineHeight = Math.max(lineHeight, childHeight);
                    lineWidth = lineWidth + childWidth;
                }
                // 如果是最后一个，则将当前记录的最大宽度和当前lineWidth做比较
                if (i == childCount - 1)
                {
                    width = Math.max(width, lineWidth);
                    height += lineHeight;
                }
            }
        }

        int reallyWidth = widthMode == MeasureSpec.EXACTLY?sizeWidth:width;
        int reallyHeight = heightMode == MeasureSpec.EXACTLY?sizeHeight:height;
        Log.e(TAG, "measure width :" + reallyWidth + "\nheight:" + reallyHeight);
        Log.e(TAG, "ameasure width :" + width + "\nheight:" + height);
        setMeasuredDimension(reallyWidth, reallyHeight);
    }

    private List<List<View>> allViews = new ArrayList<List<View>>();
    private List<Integer> lineHeights = new ArrayList<Integer>();
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        allViews.clear();
        lineHeights.clear();

        int width = getMeasuredWidth();
        List<View> lineViews = new ArrayList<View>();
        int height = 0;
        int lineWidth = 0;
        int count = getChildCount();
        for(int i = 0;i < count; i++){
            View view = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();
            if(lineWidth + view.getMeasuredWidth() + lp.leftMargin + lp.rightMargin > width){
                allViews.add(lineViews);
                lineHeights.add(height);
                lineWidth = 0;
                height = 0;
                lineViews = new ArrayList<>();
            }
            lineViews.add(view);
            lineWidth = lineWidth + view.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            height = Math.max((view.getMeasuredHeight() + lp.topMargin + lp.bottomMargin),height);
        }

        allViews.add(lineViews);
        lineHeights.add(height);

        int left = 0;
        int top = 0;

        int linesNum = allViews.size();
        Log.e(TAG, "layout lineNum is  :" + linesNum);
        for (int i = 0;i<linesNum;i++){
            lineViews = allViews.get(i);
            for(int j = 0;j<lineViews.size();j++){
                View view = lineViews.get(j);
                MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();
                if(view.getVisibility() == View.GONE){
                    continue;
                }

                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + view.getMeasuredWidth();
                int bc = tc + view.getMeasuredHeight();

                view.layout(lc, tc, rc, bc);
                Log.e(TAG, "layout left:" + lc + "\ntop:" + tc + "\nright:" + rc + "\nbottom:" + bc);

                left = left + lp.rightMargin + lp.leftMargin + view.getMeasuredWidth();

            }
            left = 0;
            top = top + lineHeights.get(i);
        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        MarginLayoutParams lp = new MarginLayoutParams(getContext(),attrs);
        return lp;
    }
}
