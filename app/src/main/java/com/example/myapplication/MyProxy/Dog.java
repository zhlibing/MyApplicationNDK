package com.example.myapplication.MyProxy;

public class Dog implements Animal {
    @Override
    public void run(String speed) {
        System.out.println(speed);
    }

    @Override
    public String speak(String word) {
        return word;
    }
}
