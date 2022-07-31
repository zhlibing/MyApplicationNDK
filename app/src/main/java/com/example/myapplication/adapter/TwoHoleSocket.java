package com.example.myapplication.adapter;

public class TwoHoleSocket extends Socket {
    public TwoHoleSocket(int hole) {
        super(hole);
    }

    @Override
    void provideElectric() {
        System.out.println("我提供两孔插座供电能力");
    }
}
