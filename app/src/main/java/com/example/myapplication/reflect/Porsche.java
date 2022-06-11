package com.example.myapplication.reflect;

public class Porsche implements HIDL<Porsche> {
    @Override
    public String manufactory() {
        return "保时捷中国";
    }

    @Override
    public int maxSpeed() {
        return 300;
    }

    @Override
    public void run() {
        System.out.println(this.manufactory() + "跑的飞快");
    }

    @Override
    public Porsche numbers() {
        System.out.println("我干了其他的事情");
        return new Porsche();
    }
}
