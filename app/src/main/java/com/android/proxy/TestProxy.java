package com.android.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by peiboning on 2017/7/6.
 */

public class TestProxy {
    public static void testStudent(){
        IPerson stu = new Student();
        IPerson proxy = (IPerson) Proxy.newProxyInstance(stu.getClass().getClassLoader(), stu.getClass().getInterfaces(), new StudentHandler(stu));
        proxy.work();
    }
}
