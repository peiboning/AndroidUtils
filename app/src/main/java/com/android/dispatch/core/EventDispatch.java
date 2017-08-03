package com.android.dispatch.core;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import com.android.dispatch.BaseEvent;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by peiboning on 2017/7/28.
 */

public class EventDispatch {
    private static int sIndex = 0;
    private Handler mH;
    private Handler tH;
    private HandlerThread t;

    private ArrayList<WeakReference<ISubscriber>> mMainSubsctibers;
    private ArrayList<WeakReference<ISubscriber>> mSubsctibers;

    public EventDispatch(){
        mH = new Handler(Looper.getMainLooper());
        t = new HandlerThread("EventDispatch#" + sIndex++);
        t.start();
        tH = new Handler(t.getLooper());

        mMainSubsctibers = new ArrayList<WeakReference<ISubscriber>>(30);
        mSubsctibers = new ArrayList<WeakReference<ISubscriber>>(30);
    }

    public synchronized void post(BaseEvent event, boolean isMainThread){
        if(null != event){
            if(isMainThread){
                dispatchInMainThread(event);
            }
        }
    }

    private void dispatchInMainThread(final BaseEvent event){

        mH.post(new RunnableWraper(new Runnable() {
            @Override
            public void run() {
                dispatchMainEvent(event);
            }
        }, event.getClass().getName()));
    }

    private void dispatchMainEvent(BaseEvent event){
        synchronized (mMainSubsctibers){
            if(mMainSubsctibers.size() > 0){
                WeakReference<ISubscriber> subscriber;
                for(int i = 0;i<mMainSubsctibers.size();i++){
                    subscriber = mMainSubsctibers.get(i);
                    if(null != subscriber && null != subscriber.get()){
                        subscriber.get().onEvent(event);
                    }
                }

            }
        }
    }

    private void dispatchEvent(BaseEvent event){
        synchronized (mMainSubsctibers){
            if(mMainSubsctibers.size() > 0){
                WeakReference<ISubscriber> subscriber;
                for(int i = 0;i<mMainSubsctibers.size();i++){
                    subscriber = mMainSubsctibers.get(i);
                    if(null != subscriber && null != subscriber.get()){
                        subscriber.get().onEvent(event);
                    }
                }

            }
        }
    }


    private class RunnableWraper implements Runnable{
        private Runnable runImpl;
        private String eventClassName;
        RunnableWraper(Runnable runnable, String eventClassName){
            this.runImpl = runnable;
            this.eventClassName = eventClassName;
        }
        @Override
        public void run() {
            long start = System.currentTimeMillis();
            if(null != runImpl){
                runImpl.run();
            }
            long cost = System.currentTimeMillis() - start;
            if(cost>30){
                if(Looper.myLooper() == Looper.getMainLooper()){
                    Log.w("RunnableWraper",eventClassName+":(MainThread) onEvent execute time is more than 30ms(real cost time is "+cost+"ms),please check");
                }else{
                    Log.w("RunnableWraper",eventClassName+": onEvent execute time is more than 30ms(real cost time is "+cost+"ms),please check");
                }
            }
        }
    }





}
