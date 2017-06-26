package com.android.utils;

/**
 * Created by peiboning on 2017/6/26.
 */

public abstract class SingleTon<T> {
    private T sInstance;

    public abstract T create();

    public final T get(){
        if(null == sInstance){
            synchronized (this){
                if(null == sInstance){
                    sInstance = create();
                }
            }
        }
        return sInstance;
    }


}
