package com.view_anniation;

import java.util.Random;

/**
 * Created by peiboning on 2017/7/27.
 */

public class ConstructArray {
    public static void construct(){
        new ConstructArray().test();
    }

    private void test(){
        run();
    }

    private void run(){
        int[] arr = new int[10000];
        Random random = new Random();
        int length = arr.length;
        long start = System.currentTimeMillis();
        int temp = 0;
        for(int i = 0;i<length;i++){
            int value = random.nextInt(length) + 1;
            if(arr[value-1] == 0){
                arr[value-1] = value;
            }else{
                i--;
            }
            temp++;
        }

        long cost = System.currentTimeMillis() - start;
        System.out.println("temp value is " + temp);
        System.out.println("cost time is " + cost);
        print(arr);
    }



    private void print(int[] arr){
        for(int i = 0;i<arr.length;i++){
            if(i != 0 && i%10 == 0){
                System.out.print("\n");
            }
            System.out.print(arr[i]+",");
        }
    }
}
