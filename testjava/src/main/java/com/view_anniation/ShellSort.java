package com.view_anniation;

/**
 * Created by peiboning on 2017/7/27.
 */

public class ShellSort {
    public static void sort(){
        new ShellSort().run();
    }

    private void run(){
        int[] arr = {2,3,100, 5, 10, 30, 20, 15};
        int h = 1;
        while (h<=arr.length/3){
            h = 3*h + 1;
        }

        while (h>0){
            for(int i = h;i<arr.length;i = i + h){
                int key = arr[i];
                int j = i - h;
                while (j>=0 && arr[j] > key){
                    arr[j+h] = arr[j];
                    j = j - h;
                }
                arr[j + h] = key;
            }
            h = (h-1)/3;
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
