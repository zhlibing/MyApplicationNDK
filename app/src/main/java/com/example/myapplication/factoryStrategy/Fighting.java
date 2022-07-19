package com.example.myapplication.factoryStrategy;

public interface Fighting<T extends Weapon> {
    void fight(T t);
}
