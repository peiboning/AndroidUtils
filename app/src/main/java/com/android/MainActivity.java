package com.android;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.proxy.TestProxy;
import com.android.video.VideoActivity;
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
}
