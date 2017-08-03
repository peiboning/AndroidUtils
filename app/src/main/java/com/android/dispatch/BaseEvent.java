package com.android.dispatch;

/**
 * Created by peiboning on 2017/7/28.
 */

public abstract class BaseEvent {
    public Object data;
    public int what;

    protected BaseEvent(Object data){
        this.data = data;
    }
}
