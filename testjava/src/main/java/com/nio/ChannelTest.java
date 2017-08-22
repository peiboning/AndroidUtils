package com.nio;

/**
 * Created by peiboning on 2017/8/11.
 */

public class ChannelTest {
    public static void main(String[] args){
        for(int i = 0;i<10;i++){
            for(int j = i+1;j<20;j++){
                if(j == 5){
                    break;
                }
                System.out.println("in: " + j);
            }
            System.out.println("out:" + i);
        }
    }
}
