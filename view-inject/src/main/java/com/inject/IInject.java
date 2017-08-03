package com.inject;


/**
 * Created by peiboning on 2017/7/26.
 */

public interface IInject<T> {
    void inject(T host, Object object, IProvider provider);
}
