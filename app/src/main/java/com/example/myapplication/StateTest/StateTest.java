package com.example.myapplication.StateTest;

public class StateTest {
    public static void main(String[] args) {
        Person person = new Person(new ChildState());
        person.setPersonState(new GrowupState());
        person.setPersonState(new MiddleState());
        person.setPersonState(new OldState());
    }
}
