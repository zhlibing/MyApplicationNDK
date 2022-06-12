package com.example.myapplication.semaphore;

import java.util.concurrent.Semaphore;

public class Hotal {
    public static void main(String[] args) {
        /**
         * 酒店有五个洗手池
         */
        Semaphore semaphore = new Semaphore(5);
        /**
         * 来了一定数量的顾客需要用洗手池
         */
        for (int i = 0; i < 100; i++) {
            new Thread(new customer(semaphore, "客人" + i)).start();
        }
        while (semaphore.getQueueLength() > 0) {
            System.out.println("洗手池正在被占用" + semaphore.getQueueLength());
        }
        if (!semaphore.hasQueuedThreads()) {
            System.out.println("客人全部洗完手了，" + semaphore.getQueueLength() + "个洗手盆被占用");
        }
    }
}
