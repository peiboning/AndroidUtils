package com.android.utils.bitmap.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.utils.System.App;
import com.android.utils.bitmap.Cache;

/**
 * Created by peiboning on 2017/6/26.
 */

public class BitmapCahce implements Cache<String, Bitmap> {

    private LruCache<String, Bitmap> mCache;
    public BitmapCahce(){
        mCache = new LruCache<String, Bitmap>((int) (App.maxMemery/8)){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                int value = 0;
                if(null != bitmap){
                    value = bitmap.getByteCount();
                }
                return value;
            }
        };
    }


    @Override
    public void put(String key, Bitmap bitmap) {
        mCache.put(key, bitmap);
    }

    @Override
    public Bitmap get(String key) {
        return mCache.get(key);
    }
}
