package com.example.myapplication.reflect;

public class BMW implements HIDL<Integer> {
    @Override
    public String manufactory() {
        return "宝马中国";
    }

    @Override
    public int maxSpeed() {
        return 200;
    }

    @Override
    public void run() {
        System.out.println(this.manufactory() + "家用型操控轿车");
    }

    @Override
    public Integer numbers() {
        System.out.println("乘坐人数5");
        return 5;
    }
}
