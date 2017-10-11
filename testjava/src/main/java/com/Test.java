package com;

/**
 * Created by peiboning on 2017/10/10.
 */

public class Test {
    public static void main(String[] args){
        String a = "var a = document.getElementById(\"POS\"); a.click();";
        char[] arrs = a.toCharArray();
        String b = new String(arrs);
        System.out.print(b);
    }
}
