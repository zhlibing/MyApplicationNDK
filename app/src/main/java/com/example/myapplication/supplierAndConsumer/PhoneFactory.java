package com.example.myapplication.supplierAndConsumer;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.function.Supplier;

@RequiresApi(api = Build.VERSION_CODES.N)
public class PhoneFactory implements Supplier<Phone> {
    private Phone phone;

    @Override
    public Phone get() {
        Constant.SUPPLIER_COUNT++;
        System.out.println(PhoneFactory.class.getSimpleName() + "我生产了一部手机,总数" + Constant.SUPPLIER_COUNT);
        this.phone = new Phone();
        return this.phone;
    }
}
