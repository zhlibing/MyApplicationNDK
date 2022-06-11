package com.example.myapplication.enumTest;

public enum MyEnum {
    Instance,
    ONE("张三", 2),
    TWO("李四", 12);
    String name;
    int age;

    MyEnum() {
    }

    MyEnum(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        System.out.println(ONE.name + ONE.age);
        System.out.println(TWO.name + TWO.age);
        System.out.println(Instance.name + Instance.age);
    }
}
