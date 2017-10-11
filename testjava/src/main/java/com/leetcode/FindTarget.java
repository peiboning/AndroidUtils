package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by peiboning on 2017/8/28.
 * 给出一个整形数组和一个目标值，找出和等于目标值的下标（唯一值）
 * {4,10,5,9, 3, 30} target = 14
 * result={0,1}
 * https://leetcode.com/problems/two-sum/solution/
 */

public class FindTarget {

    public static void main(String[] args){
        int[] arrs = {4,10,5,9, 3, 30};
        int target = 14;
//        int[] res = new FindTarget().twoSum1(arrs, target);
        int[] res = new FindTarget().twoSum2(arrs, target);
        System.out.print("first index is :" + res[0] + "\nsecond index is :" + res[1]);
    }

    //空间换时间
    public int[] twoSum1(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[1] = i + 1;
                result[0] = map.get(target - numbers[i]);
                return result;
            }
            map.put(numbers[i], i + 1);
        }
        return result;
    }

    //时间换空间
    public int[] twoSum2(int[] numbers, int target){
        int[] res = new int[2];
        A:
        for(int i = 0;i<numbers.length;i++){
            for(int j = i+1;j<numbers.length;j++){
                if(numbers[i] + numbers[j] == target){
                    res[0] = i;
                    res[1] = j;
                    break A;
                }
            }
        }
        return res;
    }
}
