package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peiboning on 2017/8/28.
 * 求最大不连续子串
 */

public class FindMaxSubStr {
    public static void main(String[] args){
        System.out.print("max length is " + new FindMaxSubStr().findMaxSub("bbbbbb"));
    }

    public int findMaxSub(String targetStr){
        int length = 0;
        char[] r = targetStr.toCharArray();
        List<Character> list = new ArrayList<>();
        for(int i = 0;i<r.length;i++){
            if(list.contains(r[i])){
                if(list.size()>length){
                    length = list.size();
                }
                list.clear();
                list.add(r[i]);
                continue;
            }else{
                list.add(r[i]);
                if(list.size()>=length){
                    length = list.size();
                }
            }
        }
        return length;
    }
}
