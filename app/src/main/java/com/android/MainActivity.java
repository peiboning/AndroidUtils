package com.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.com.android.R;
import com.android.proxy.TestProxy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testProxy(View view){
        TestProxy.testStudent();
    }
}
