package com.android;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.TextureView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sohu.util.demo.R;

public class AnimationActivity extends Activity {

    private LinearLayout rootView;
    private TextView tv;
    private boolean isVisvible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        rootView = (LinearLayout) findViewById(R.id.rootView);
        tv = (TextView) findViewById(R.id.tv);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void begin(View view){
        TransitionManager.beginDelayedTransition(rootView);
        tv.setVisibility(isVisvible ? View.VISIBLE:View.GONE);
        isVisvible = !isVisvible;
    }
}
