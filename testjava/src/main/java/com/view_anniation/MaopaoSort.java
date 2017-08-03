package com.view_anniation;

/**
 * Created by peiboning on 2017/7/27.
 */

public class MaopaoSort {
    public static void sort(){
        new MaopaoSort().run();
    }

    private void run(){
        int[] arr = {2,3,100, 5, 10, 30, 20, 15, -1, 1000, -50};
        for(int i = 0;i<arr.length;i++){
            boolean isSwap = false;
            for(int j = 0;j<arr.length -1;j++){
                if(arr[j] < arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSwap = true;
                }
            }
            if(!isSwap){
                break;
            }
            print(arr);

        }
//        print(arr);
    }
    private void print(int[] arr){
        for(int i : arr){
            System.out.print(i+",");
        }
        System.out.print("\n");
    }
}
