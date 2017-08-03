package com.android.video;

/**
 * Created by peiboning on 2017/8/2.
 */

public class TimeUtils {
    public synchronized static String getTimeStr(int time){
        if(time<=0){
            return "00:00:00";
        }
        int h = time / 1000 / 60/ 60;
        int m = (time-(h*60*60*1000))/1000/60;
        int s = (time - ((h*60*60*1000) + (m * 60 * 1000)))/1000;
        String strH = h<=9?"0" + h:h+"";
        String strM = m<=9?"0" + m:m+"";
        String strs = s<=9?"0" + s:s+"";
        return strH + ":" + strM + ":" + strs;
    }

    public static void main(String[] args){
        int value = 10 * 60 * 1000 + 5 * 1000;
        System.out.print("test:" + getTimeStr(value));
    }
}
