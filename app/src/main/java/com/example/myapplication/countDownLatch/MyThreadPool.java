package com.example.myapplication.countDownLatch;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {

    private int TASK_TOTAL = 100;
    private int AREA = 10;
    private ThreadPoolExecutor executorService;
    private static CountDownLatch countDownLatch;
    private static Map<String, String> map = new ConcurrentHashMap<>();

    private void initPool() {
        int COUNT = TASK_TOTAL / AREA;
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(COUNT, COUNT + 1, 1000 * 10, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        }
        executorService.prestartAllCoreThreads();
        countDownLatch = new CountDownLatch(COUNT);
        System.out.println("线程总数：" + COUNT);
        for (int i = 0; i < COUNT; i++) {
            int INDEX = AREA * i;
            executorService.execute(new MyRunnable(INDEX));
        }
    }

    class MyRunnable implements Runnable {
        private int INDEX = 0;
        private int LOCAL_COUNT;

        public MyRunnable(int INDEX) {
            this.INDEX = INDEX;
        }

        @Override
        public void run() {
            do {
                INDEX++;
                LOCAL_COUNT++;
                map.put(String.valueOf(this.INDEX), Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + ">>" + INDEX);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (LOCAL_COUNT < AREA);

            countDownLatch.countDown();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool();
        myThreadPool.initPool();
        try {
            countDownLatch.await();
            System.out.println("所有线程执行完毕,当前是" + Thread.currentThread().getName());
            System.out.println("Map大小：" + map.size());
            myThreadPool.shutdown();

            Iterator<Map.Entry<String, String>> entryIterator = map.entrySet().iterator();
            while (entryIterator.hasNext()) {
                HashMap.Entry<String, String> entry = entryIterator.next();
                System.out.println("key:" + entry.getKey() + "  value:" + entry.getValue());
            }

            List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
                @Override
                public int compare(Map.Entry<String, String> t0, Map.Entry<String, String> t1) {
                    return Integer.parseInt(t0.getKey()) - Integer.parseInt(t1.getKey());
                }
            });

            System.out.println("list大小：" + list.stream().count());

            for (int i = 0; i < list.size(); i++) {
                System.out.println("key2:" + list.get(i).getKey() + "  value2:" + list.get(i).getValue());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 记得关闭
     */
    private void shutdown() {
        executorService.shutdown();
    }
}
