package com.example.myapplication.StateTest;

public class Person {
    PersonState personState;

    public Person(PersonState personState) {
        this.personState = personState;
        notifyStateChanged();
    }

    private void notifyStateChanged() {
        personState.state();
    }

    public PersonState getPersonState() {
        return personState;
    }

    public void setPersonState(PersonState personState) {
        this.personState = personState;
        notifyStateChanged();
    }
}
