package com.example.myapplication.reflect;

public class PorscheFactory extends HidlFactory {

    private PorscheFactory() {

    }

    public static PorscheFactory getFactory() {
        return new PorscheFactory();
    }

    @Override
    HIDL manufactured() {
        return new Porsche();
    }

    @Override
    void init() {
        System.out.println("do something else");
    }
}
