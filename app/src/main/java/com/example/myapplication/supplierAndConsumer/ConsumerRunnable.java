package com.example.myapplication.supplierAndConsumer;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class ConsumerRunnable implements Runnable{
    private Phone phone;

    public ConsumerRunnable(Phone phone) {
        this.phone = phone;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        new ConsumerPerson().accept(phone);
    }
}
