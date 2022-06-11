package com.example.myapplication.ThreadLocal;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class Main {
    public static int myNum;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
//            new Thread(new MyRunnable()).start();
            new Thread(new MyRunnable2()).start();
//            new Thread(new MyRunnable3()).start();
        }
        try {
            Thread.sleep(2000);
            System.out.println("main方法的："+myNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        new MyRunnable().stat();
    }
}
