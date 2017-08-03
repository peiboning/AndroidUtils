package com.android.video;

import android.view.View;
import android.view.animation.AlphaAnimation;

/**
 * Created by peiboning on 2017/8/3.
 */

public class AnimalUtils {
    public static void alphView(View view){
        AlphaAnimation animation = new AlphaAnimation(0.0f, 0.9f);
        animation.setDuration(500);
        view.setAnimation(animation);
    }
    public static void alphGone(View view){
        AlphaAnimation animation = new AlphaAnimation(0.9f, 0.0f);
        animation.setDuration(500);
        view.setAnimation(animation);
    }
}
