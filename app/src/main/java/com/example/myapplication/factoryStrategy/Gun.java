package com.example.myapplication.factoryStrategy;

public class Gun extends Weapon {
    @Override
    void rangedAttract() {
        System.out.println("我可以远程攻击");
    }

    @Override
    void closeAttract() {

    }
}
