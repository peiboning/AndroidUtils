package com.leetcode;

/**
 * Created by peiboning on 2017/8/29.
 */

public class StrMatcher {
    public static void main(String[] args){

    }

    public boolean isMatch(String s1, String s2){
        if(s1 == s2){
            return true;
        }
        if(null == s1){
            return false;
        }

        if(null == s2){
            return false;
        }

        if(s1.equals(s2)){
            return true;
        }


        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int length = Math.min(arr1.length, arr2.length);
        for(int i = 0;i<length;i++){
            if(arr1[i] == '*'){

            }
        }



        return false;

    }
}
