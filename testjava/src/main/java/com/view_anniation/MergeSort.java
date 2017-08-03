package com.view_anniation;

/**
 * Created by peiboning on 2017/7/21.
 */

public class MergeSort {
    public static void sort(){
        new MergeSort().run();
    }

    private void run(){
        int[] arr = {8,1,3,2,7,4,0,9,6,5,8,1,3,2,7,4,0,9,6,5};
        mergeSort(arr, 0, arr.length-1);
    }

    private void mergeSort(int[] arr, int low, int length) {
        if(low>=length){
            return;
        }
        int mid = (low + length)/2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, length);
        merge(arr, low, mid, length);
        print(arr);
    }

    private void merge(int[] arr,int low, int center, int length) {
        int[] temp = new int[arr.length];

        int rightLow = center + 1;
        int head = low;
        int tempHead = low;
        while(low<=center && rightLow<=length){
            if(arr[low]<arr[rightLow]){
                temp[head++] = arr[low++];
            }else{
                temp[head++] = arr[rightLow++];
            }
        }

        while (low<=center){
            temp[head++] = arr[low++];
        }

        while (rightLow<=length){
            temp[head++] = arr[rightLow++];
        }

        while (tempHead<=length){
            arr[tempHead] = temp[tempHead++];
        }
    }

    private void print(int[] arr){
        for(int i : arr){
            System.out.print(i+",");
        }
        System.out.print("\n");
    }
}
