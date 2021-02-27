package com.commax.sample;

public class CommonUtils {
    public static long startTime;

    public static void exampleStart(){
        startTime = System.currentTimeMillis();
    }

    public static void sleep(int mills){
        try{
            Thread.sleep(mills);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
