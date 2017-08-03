package com.view_anniation;

/**
 * Created by peiboning on 2017/7/27.
 */

public class FindMaxCha {
    public static void find(){
        new FindMaxCha().run();
    }

    private record findGreatestSumOfSubArray(int[] array) {
        if (array.length==0 || array==null) {
            return null;
        }
        int currentSum = 0;
        int max = 0;
        int begin = 0;
        int end = 0;
//        for (int i = 0; i < array.length; i++) {
//            if(currentSum<=0){
//                currentSum = array[i];
//                begin = i;
//            }else{
//                currentSum += array[i];
//            }
//            if(currentSum>max){
//                end = i;
//                max = currentSum;
//            }
//        }
        record r = new record(begin, end, max);
        return r;
    }

    private void run(){
        int[] arr = {-1, 100, 20, -1, 60,-1, 80, 100, -80, 0, 200};
        System.out.println(findGreatestSumOfSubArray(arr).toString());
    }
    private class record implements Comparable<record>{
        int begin;
        int end;
        int max;
        public record(int begin, int end, int max){
            this.begin = begin;
            this.end = end;
            this.max = max;
        }


        @Override
        public int compareTo(record record) {
            return record.max - this.max;
        }

        @Override
        public String toString() {
            return "record{" +
                    "begin=" + begin +
                    ", end=" + end +
                    ", max=" + max +
                    '}';
        }
    }
}
