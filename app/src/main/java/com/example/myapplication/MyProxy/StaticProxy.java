package com.example.myapplication.MyProxy;

public class StaticProxy implements Animal {
    Animal animal;

    public StaticProxy(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void run(String speed) {
        animal.run(speed);
    }

    @Override
    public String speak(String word) {
        return animal.speak(word);
    }
}
