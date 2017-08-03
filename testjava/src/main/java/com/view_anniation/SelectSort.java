package com.view_anniation;

/**
 * Created by peiboning on 2017/7/27.
 */

public class SelectSort {
    public static void sort(){
        new SelectSort().run();
    }

    private void run(){
        int[] arr = {2,3,100, 5, 10, 30, 20, 15};

        for(int i = 0;i<arr.length-1;i++){
            int minPos = i;
            for(int j = i+1;j<arr.length;j++){
                if(arr[j] < arr[minPos]){
                    minPos = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minPos];
            arr[minPos] = temp;
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
