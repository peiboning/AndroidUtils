package com.android;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.proxy.TestProxy;
import com.android.usageStatesmanager.UsageStateActivity;
import com.android.video.VideoActivity;
import com.android.view.CircleView;
import com.sohu.util.demo.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testProxy(View view){
        TestProxy.testStudent();
    }

    public void gotoVideo(View view){
        Intent intent = new Intent(this,VideoActivity.class);
        startActivity(intent);
    }

    public void tranction(View view){
        Intent intent = new Intent(this,AnimationActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    public void flowView(View view){
        Intent intent = new Intent(this,FlowViewActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    public void circle(View view){
        Intent intent = new Intent(this,CircleActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    public void usageState(View view){
        Intent intent = new Intent(this,UsageStateActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    public void webviewPage(View view){
        Intent intent = new Intent(this,WebViewActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
