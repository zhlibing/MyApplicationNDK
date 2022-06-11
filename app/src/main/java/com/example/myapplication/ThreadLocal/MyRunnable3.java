package com.example.myapplication.ThreadLocal;

import static com.example.myapplication.ThreadLocal.Main.myNum;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.HashSet;

public class MyRunnable3 implements Runnable {
    static HashSet<Integer> hashSet = new HashSet<>();
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @NonNull
        @Override
        protected Integer initialValue() {
            Integer var = 0;
            sycAdd(var);
            return 0;
        }
    };

    private synchronized static void sycAdd(Integer var) {
        hashSet.add(var);
        System.out.println("执行sycAdd" + hashSet.size() + "myNum:" + myNum);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        addMed();
        System.out.println("执行" + hashSet.size() + ">>" + Thread.currentThread().getName());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addMed() {
        Integer var = threadLocal.get();
        for (int i = 0; i < 100; i++) {
            myNum++;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(var + ">>" + Thread.currentThread().getName() + "Num:" + myNum);
    }
}
