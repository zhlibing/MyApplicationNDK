package com.example.myapplication.adapter;

public class ThreeHoleSocket extends Socket{
    public ThreeHoleSocket(int hole) {
        super(hole);
    }

    @Override
    void provideElectric() {
        System.out.println("我提供三孔插座供电能力");
    }
}
