package com.example;

/**
 * Created by peiboning on 2017/7/21.
 */

public class QuickSort {
    public static void sort(){
        new QuickSort().run();
    }

    private void run(){
        int[] arr = {8,1,3,2,7,4,0,9,6,5};
        quickSort(arr, 0, arr.length-1);
        print(arr);
    }

    private void quickSort(int[] arr, int low, int high) {
        if(low>=high){
            return;
        }
        int key = arr[low];
        int i = low;
        int j = high;
        while (i<j){
            while (i<j && arr[j]>key){
                j--;
            }

            arr[i] = arr[j];

            while (i<j && arr[i]<key){
                i++;
            }

            arr[j] = arr[i];

        }
        arr[i] = key;

        quickSort(arr, low, i-1);
        quickSort(arr, i+1, high);

    }

    private void print(int[] arr){
        for(int i : arr){
            System.out.print(i+",");
        }
        System.out.print("\n");
    }
}
