package com.example.myapplication.supplierAndConsumer;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.function.Consumer;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ConsumerPerson implements Consumer<Phone> {

    @Override
    public void accept(Phone phone) {
        /**
         * 因为线程每次都是新的消费者，lock和对象锁锁不住，要锁类才锁的住
         */
        synchronized (ConsumerPerson.class) {
            Constant.CONSUMER_COUNT++;
            System.out.println(ConsumerPerson.class.getSimpleName() + "我消费了一步手机,总数：" + Constant.CONSUMER_COUNT);
        }
    }

    @Override
    public Consumer<Phone> andThen(Consumer<? super Phone> after) {
        return Consumer.super.andThen(after);
    }
}
