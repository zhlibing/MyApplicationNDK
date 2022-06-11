package com.example.myapplication.reflect;

public abstract class HidlFactory {
    private HIDL hidl;

    abstract HIDL manufactured();

    abstract void init();
}
