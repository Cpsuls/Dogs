package org.example.doogas.State;

public class HappyState implements DogState{
    @Override
    public String play() {
        return "Dog is playful!";
    }

    @Override
    public String feed() {
        return "Dog eats";
    }

    @Override
    public String voice() {
        return "Dog is woofing playfully!";
    }
}
