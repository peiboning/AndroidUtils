package com.android.utils.bitmap;

/**
 * Created by peiboning on 2017/6/26.
 */

public interface Cache<K,V> {
    void put(K key, V vaule);
    V get(K key);
}
