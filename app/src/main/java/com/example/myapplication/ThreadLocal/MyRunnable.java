package com.example.myapplication.ThreadLocal;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.HashSet;

public class MyRunnable implements Runnable {
    static HashSet<Var<Integer>> hashSet = new HashSet<>();
    static ThreadLocal<Var<Integer>> threadLocal = new ThreadLocal<Var<Integer>>() {
        @NonNull
        @Override
        protected Var<Integer> initialValue() {
            Var<Integer> var = new Var<>();
            var.setT(0);
            sycAdd(var);
            return var;
        }
    };

    private synchronized static void sycAdd(Var var) {
        hashSet.add(var);
        System.out.println("执行sycAdd" + hashSet.size());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        addMed();
        System.out.println("执行" + hashSet.size() + ">>" + Thread.currentThread().getName());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addMed() {
        Var<Integer> var = threadLocal.get();
        var.setT(var.getT() + 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Integer stat() {
        int res = hashSet.stream().map(x -> x.getT()).reduce((a, x) -> a + x).get();
        System.out.println(res + "<<<<<");
        return res;
    }
}
