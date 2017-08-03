package com.view_anniation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by peiboning on 2017/7/27.
 * 基数排序
 */

public class JishuSort {
    public static void sort(){
        int[] arr = {2,3,50534, 5, 10, 30, 20, 15};
        new JishuSort().run(arr, 0);
        print(arr);
    }

    private static void print(int[] arr){
        for(int i : arr){
            System.out.print(i+",");
        }
        System.out.print("\n");
    }

    private void run(int[] arr, int temp){
        if(temp > 5){
            return;
        }
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int num : arr){
            int index = getFist(num, temp);
            List<Integer> list = map.get(index);
            if(null == list){
                list = new ArrayList<>();
            }
            list.add(num);
            map.put(index, list);
        }
        if(setArr(map, arr)){
            System.out.println("------" + temp);
            run(arr, ++temp);
        }
    }

    private boolean setArr(HashMap<Integer, List<Integer>> map, int[] arr) {
        int index = 0;
        for(int i = 0;i<=9;i++){
            List<Integer> list = map.get(i);
            if(i == 0 && list.size() == arr.length){
               return false;
            }
            if(null != list && list.size()>0){
                for(int a : list){
                    arr[index++] = a;
                }
            }
        }
        return true;
    }

    private int getFist(int num, int index){
        int temp = 1;
        for(int i = 0;i<index;i++){
            temp = temp * 10;
        }
        return num / temp % 10;
    }
}
