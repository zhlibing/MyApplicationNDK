package com.example.myapplication.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    static class TaskThread extends Thread {

        CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + " 出发饭店");
                barrier.await();
                System.out.println(getName() + " 到达饭店");

                Thread.sleep(2000);
                System.out.println(getName() + " 开始吃饭");
                barrier.await();
                System.out.println(getName() + " 吃完饭！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadNum = 5;
//        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + " 完成最后任务");
//            }
//        });
        CyclicBarrier barrier = new CyclicBarrier(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }
    }

}