package org.example.doogas.State;

public class SleepyState implements DogState {

    @Override
    public String play() {
        return "Dog is sleeping";
    }

    @Override
    public String feed() {
        return "Dog is sleeping and does not eat";
    }

    @Override
    public String voice() {
        return "Zzzz-zzzz-zzz";
    }
}
