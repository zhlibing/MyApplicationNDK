package com.example.myapplication.factoryStrategy;

public class Knife extends Weapon {
    @Override
    void rangedAttract() {

    }

    @Override
    void closeAttract() {
        System.out.println("我可以近距离攻击");
    }
}
