package com.example.myapplication.reflect;

public class BMWFactory extends HidlFactory {

    {
        init();
    }

    private BMWFactory() {
    }

    public static BMWFactory getFactory() {
        return new BMWFactory();
    }

    @Override
    public void init() {
        System.out.println("do something");
    }

    @Override
    HIDL manufactured() {
        return new BMW();
    }
}
