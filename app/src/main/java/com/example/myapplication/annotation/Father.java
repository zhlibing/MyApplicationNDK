package com.example.myapplication.annotation;

public class Father {
    String name;
    int age;

    public String getName() {
        return name;
    }

    /**
     * 返回对象自己就成了建造者模式
     * @param name
     * @return
     */
    public Father setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Father setAge(int age) {
        this.age = age;
        return this;
    }

    public static Father Builder() {
        Father father = new Father();
        return father;
    }

    public static void main(String[] args) {
        Father father = Builder().setAge(40).setName("zhang san");
        System.out.println(father.age + father.name);
    }
}
