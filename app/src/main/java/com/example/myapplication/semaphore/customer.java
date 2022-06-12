package com.example.myapplication.semaphore;

import java.util.concurrent.Semaphore;

public class customer implements Runnable {
    private Semaphore washbinSemaphore;
    private String name;

    public customer(Semaphore washbinSemaphore, String name) {
        this.washbinSemaphore = washbinSemaphore;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            washbinSemaphore.acquire();
            System.out.println(name + "开始洗手");
            Thread.sleep(500);
            System.out.println(name + "洗完手了");
            washbinSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
