package com.android.utils.database;

import android.database.sqlite.SQLiteDatabase;

import com.android.utils.SingleTon;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 支持多线程的数据库操作类
 * Created by peiboning on 2017/6/26.
 */

public class MyDatabase {
    private CustomDatabaseHelper helper;
    private SQLiteDatabase database;
    private AtomicInteger openTimes;

    private static SingleTon<MyDatabase> singleTon;

    static {
        singleTon = new SingleTon<MyDatabase>() {
            @Override
            public MyDatabase create() {
                return new MyDatabase();
            }
        };
    }

    private MyDatabase() {
        helper = new CustomDatabaseHelper(null, null, null, 10);
        openTimes = new AtomicInteger(0);
    }



    public static MyDatabase getInstance(){
        return singleTon.get();
    }

    public synchronized SQLiteDatabase getDatabase(){
        if(openTimes.getAndIncrement() == 0){
            database = helper.getWritableDatabase();
        }

        return database;
    }

    public synchronized void close(){
        if(openTimes.getAndDecrement() == 1){
            database.close();
        }
    }

}
