package com.commax.debugging;

import java.io.IOException;
import java.net.InetAddress;

public class CommonUtils {

    public static final int ERROR_CODE = -1;

    public static long startTime;
    public static void exampleStart(){
        startTime = System.currentTimeMillis();
    }
    public static void sleep(int millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public static boolean isNetworkAvailable() {
        try {
            return InetAddress.getByName("www.google.com").isReachable(1000);
        } catch (IOException e) {
            System.out.println("Network is not available");
        }
        return false;
    }

}
