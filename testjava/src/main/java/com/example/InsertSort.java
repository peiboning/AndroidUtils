package com.example;

/**
 * Created by peiboning on 2017/7/21.
 */

public class InsertSort {
    public static void sort(){
        new InsertSort().run();
    }

    private void run(){
        int[] arr = {8,1,3,2,7,4,0,9,6,5};

        for(int i = 1;i<arr.length-1;i++){
            int j = i-1;
            int temp = arr[i];
            for(;j>=0&&arr[j]>=temp;j--){
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;
        }

        print(arr);


    }

    private void print(int[] arr){
        for(int i : arr){
            System.out.print(i+",");
        }
        System.out.print("\n");
    }
}
