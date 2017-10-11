package com.leetcode;

/**
 * Created by peiboning on 2017/9/12.
 * 反转整形数字
 */

public class ResverInteger {
    public static void main(String[] args){
        System.out.println("result : " + resver(-6543));
    }

    public static int resver(int a){
        int result = 0;
        if(a == 0){
            return 0;
        }
        boolean is = a<0;
        a = Math.abs(a);
        while (a != 0){
            int num = a%10;
            result = result*10 + num;
            a = a/10;
        }
        return is?result*(-1):result;
    }
}
