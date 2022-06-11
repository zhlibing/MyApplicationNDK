package com.example.myapplication.supplierAndConsumer;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class TestSupplierAndConsumer {
    private static int TOTAL = 10000;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        for (int i = 0; i < TOTAL; i++) {
            new Thread(new ConsumerRunnable(new PhoneFactory().get())).start();
        }
    }
}
