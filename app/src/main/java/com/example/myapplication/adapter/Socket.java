package com.example.myapplication.adapter;

public abstract class Socket {
    protected int hole;

    public Socket(int hole) {
        this.hole = hole;
    }

    abstract void provideElectric();
}
