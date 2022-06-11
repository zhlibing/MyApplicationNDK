package com.example.myapplication.ThreadLocal;

public class MyRunnable2 implements Runnable {
    private static int Num;

    @Override
    public void run() {
        addMed();
    }

    private synchronized static void addMed() {
        Num++;
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Num);
    }
}
