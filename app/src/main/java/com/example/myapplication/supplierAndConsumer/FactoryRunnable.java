package com.example.myapplication.supplierAndConsumer;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class FactoryRunnable implements Runnable{
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        new PhoneFactory().get();
    }
}
