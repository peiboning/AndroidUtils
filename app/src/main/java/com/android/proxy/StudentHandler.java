package com.android.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by peiboning on 2017/7/6.
 */

public class StudentHandler implements InvocationHandler {
    Object obj ;
    public StudentHandler(Object o){
        obj = o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getName());
        System.out.println(method.getName());



        return method.invoke(obj, args);
    }
}
