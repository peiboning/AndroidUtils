package com.leetcode;

import java.util.HashMap;

/**
 * Created by peiboning on 2017/9/12.
 */

public class TwoNum {
    public static void main(String[] args){
        int[] arr = {1, 3, 10, 3};
        int[] res = findTargetPos(arr, 100);
        for(int a : res){
            System.out.println(a);
        }
    }

    public static int[] findTargetPos(int[] arr, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0;i<arr.length;i++){
            if(map.containsKey(target-arr[i])){
                return new int[]{i, map.get(target-arr[i])};
            }else{
                map.put(arr[i], i);
            }
        }
        return new int[]{0, 0};
    }
}
