package com.android.usageStatesmanager;

import android.app.Activity;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.sohu.util.demo.R;

import java.util.List;

public class UsageStateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_state);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void test(View view){
        UsageStatsManager manager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        List<UsageStats> list = manager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, System.currentTimeMillis() - 2000, System.currentTimeMillis());

        for(UsageStats stats : list){
            System.out.println(stats.getPackageName());
        }
    }
}
